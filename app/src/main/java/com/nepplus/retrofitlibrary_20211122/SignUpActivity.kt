package com.nepplus.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityMainBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSignUp.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPW.text.toString()
            val nicname = binding.edtNickname.text.toString()
        }

    }

    override fun setValues() {

    }
}