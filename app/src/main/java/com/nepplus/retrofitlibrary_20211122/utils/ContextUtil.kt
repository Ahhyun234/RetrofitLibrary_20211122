package com.nepplus.retrofitlibrary_20211122.utils

import android.content.Context

class ContextUtil {

    companion object{
//        어떤 파일? 어떤 데이터 항목? 저장함수(setter) , 조회함수(getter)

        private val prefName = "RetorofitTestPref"

        private val TOKEN = "TOKEN"

        fun setToken(context: Context, token: String){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(TOKEN,token).apply()
        }
    }
}