package com.nepplus.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityMainBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ActivitySignUpBinding
import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnEmailCheck.setOnClickListener {

            val email = binding.edtEmail.text.toString()

            apiService.getRequestDuplicatedCheck("email",email).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if(response.isSuccessful){
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일"
                    }
                    else{
                        binding.txtEmailCheckResult.text= " 다시 검사해주세요"
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }
        binding.btnSignUp.setOnClickListener {

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