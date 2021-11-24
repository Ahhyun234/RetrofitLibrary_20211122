package com.nepplus.retrofitlibrary_20211122.pregments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nepplus.retrofitlibrary_20211122.api.ServerAPIServiceInterface
import com.nepplus.retrofitlibrary_20211122.api.ServerApi

abstract class BaseFragment :Fragment() {


    lateinit var mContext: Context

//    API 호출 명세를 담은 변수를 상속시켜줄것임
    lateinit var apiService : ServerAPIServiceInterface

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = requireContext()
        val retrofit = ServerApi.getRetrofit()
        apiService = retrofit.create(ServerAPIServiceInterface::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()

}