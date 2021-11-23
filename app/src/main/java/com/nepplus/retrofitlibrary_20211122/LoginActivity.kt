package com.nepplus.retrofitlibrary_20211122

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityLoginBinding
import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var binding: ActivityLoginBinding

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {

        val myIntent = Intent(mContext,SignUpActivity::class.java)
        startActivity(myIntent)

        }

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtID.text.toString()
            val inputPW = binding.edtPW.text.toString()

            apiService.postRequestLogin(inputEmail,inputPW).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        val basicResponse = response.body()!!
//                        Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()

//                        추가 파싱 // ~님 환영합니다.
                        val userNickname = basicResponse.data.user.nickname

                        Toast.makeText(mContext, "${userNickname}님 환영합니다.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val errorJson = JSONObject(response.errorBody()!!.string())
                        Log.d("에러경우",errorJson.toString())

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

    }
}