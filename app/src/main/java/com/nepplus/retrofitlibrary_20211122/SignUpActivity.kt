package com.nepplus.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityMainBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ActivitySignUpBinding
import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    var isDupeOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        1. 비밀번호 타이핑 하는 이벤트 => 8글자 이상인지 아닌지 검사
//        검사 결과를 txtPwCheckResult1에 반영

//        2. 비밀번호 확인 타이핑 이벤트 => 첫 비밀번호 입력과 같은지?
//        검사 결과를 txtPwCheckResult1에 반영

//        3. 회원 가입 API 호출하기 전에
//        위 1, 2 번을 통과 해야지만 서버 호출하는 것으로 변경 -> 미통과시 토스트 + 함수 강제 종료

        binding.edtEmail.addTextChangedListener {

            Log.d("입력내용",it.toString())

            binding.txtEmailCheckResult.text = "이메일 중복검사를 해주세요"
            isDupeOk = false
        }


        binding.btnEmailCheck.setOnClickListener {

            val email = binding.edtEmail.text.toString()

            apiService.getRequestDuplicatedCheck("email",email).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if(response.isSuccessful){
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일"
                        isDupeOk = true
                    }
                    else{
                        binding.txtEmailCheckResult.text= " 다시 검사해주세요"
                        isDupeOk = false
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }
        binding.btnSignUp.setOnClickListener {

            if (!isDupeOk){
                Toast.makeText(mContext, "이메일 중복검사를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPW.text.toString()
            val nicname = binding.edtNickname.text.toString()

            apiService.putRequestSignUp(email, password, nicname).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!

                        Log.d("가입자 토큰",br.data.token)
                        val signUpUserNickname = br.data.user.nickname
                        Toast.makeText(mContext, "${signUpUserNickname}님 가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })
        }

    }

    override fun setValues() {

    }
}