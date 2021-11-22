package com.nepplus.retrofitlibrary_20211122

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nepplus.retrofitlibrary_20211122.api.ServerAPIServiceInterface
import com.nepplus.retrofitlibrary_20211122.api.ServerApi

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    lateinit var apiService : ServerAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        val retrofit = ServerApi.getRetrofit()
        apiService = retrofit.create(ServerAPIService)
    }

    abstract fun setupEvents()
    abstract fun setValues()


}