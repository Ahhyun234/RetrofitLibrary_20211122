package com.nepplus.retrofitlibrary_20211122

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.sdk.user.UserApiClient
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityLoginBinding
import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import com.nepplus.retrofitlibrary_20211122.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity() {

lateinit var binding: ActivityLoginBinding
lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnKakaotalkLogin.setOnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
//                앱이 깔려있는 상황
                UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
                    if (error != null) {
                        Log.e("카톡 로그인", "로그인 실패")
                    } else if (token != null) {

                        Log.e("카톡 로그인", "로그인 성공")
                        Log.e("카톡 로그인", token.accessToken)
                        getMyInfoFromKakao()

                    }

                }

            } else {
//                앱이 안 깔려 있는 상황
                UserApiClient.instance.loginWithKakaoAccount(mContext) { token, error ->

                    if (error != null) {
                        Log.e("카톡로그인", "로그인 실패")
                    } else if (token != null) {

                        Log.e("카톡로그인", "로그인 성공")
                        Log.e("카톡로그인", token.accessToken)

                        getMyInfoFromKakao()

                    }

                }

            }

        }

        binding.btnFaceBookLogin.setOnClickListener {

//            실제 페북 로그인 실행
            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("public_profile"))
        }

        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtID.text.toString()
            val inputPW = binding.edtPW.text.toString()

            apiService.postRequestLogin(inputEmail, inputPW)
                .enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val basicResponse = response.body()!!
//                        Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()

//                        추가 파싱 // ~님 환영합니다.
                            val userNickname = basicResponse.data.user.nickname

                            Toast.makeText(mContext, "${userNickname}님 환영합니다.", Toast.LENGTH_SHORT)
                                .show()

//                            /////////토큰값 추출 -> 기기에 저장(SharedPreferences)/////////////
                            ContextUtil.setToken(mContext, basicResponse.data.token)

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)
                            finish()

                        } else {
                            val errorJson = JSONObject(response.errorBody()!!.string())
                            Log.d("에러경우", errorJson.toString())

                            val message = errorJson.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
        }

    }

    override fun setValues() {

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d("로그인", result!!.accessToken.token)
//                   1차 로그인 결과로 받은 accessToken을 내 정보를 받아오는데 사용
//                     페북 API 중 내 정보 가져오기 기능 요청
                    var graphApiRequest = GraphRequest.newMeRequest(
                        result.accessToken,
                        object : GraphRequest.GraphJSONObjectCallback {
                            override fun onCompleted(
                                jsonObj: JSONObject?,
                                response: GraphResponse?
                            ) {
                                Log.d("내 정보 요청", jsonObj.toString())
//                            우리 앱 서버에 서버 API 요청 해야 함

                                val name = jsonObj!!.getString("name")
                                val id = jsonObj.getString("id")

                                apiService.postRequestSocialLogin("facebook", id, name)
                                    .enqueue(object : Callback<BasicResponse> {
                                        override fun onResponse(
                                            call: Call<BasicResponse>,
                                            response: Response<BasicResponse>
                                        ) {
                                            if (response.isSuccessful) {
                                                val br = response.body()!!
                                                Toast.makeText(
                                                    mContext,
                                                    "${br.data.user.nickname}님 환영합니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                                /////////토큰값 추출 -> 기기에 저장(SharedPreferences)/////////////
                                                ContextUtil.setToken(mContext, br.data.token)

                                                val myIntent =
                                                    Intent(mContext, MainActivity::class.java)
                                                startActivity(myIntent)
                                                finish()
                                            }

                                        }

                                        override fun onFailure(
                                            call: Call<BasicResponse>,
                                            t: Throwable
                                        ) {

                                        }

                                    })

                            }

                        })
                    graphApiRequest.executeAsync()

                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {

                }

            })

        getKeyHash()


    }

    fun getKeyHash() {
        val info = packageManager.getPackageInfo(
            "com.nepplus.retrofitlibrary_20211122",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getMyInfoFromKakao() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("카톡 로그인", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "카톡 로그인", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"
                )

                apiService.postRequestSocialLogin(
                    "kakao",
                    user.id.toString(),
                    user.kakaoAccount?.profile?.nickname!!
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            Toast.makeText(
                                mContext,
                                "${br.data.user.nickname}님 환영합니다.",
                                Toast.LENGTH_SHORT
                            ).show()

                            /////////토큰값 추출 -> 기기에 저장(SharedPreferences)/////////////
                            ContextUtil.setToken(mContext, br.data.token)

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
            }
        }
    }
}