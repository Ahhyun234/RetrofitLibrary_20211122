package com.nepplus.retrofitlibrary_20211122

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
            super.onCreate()

        KakaoSdk.init(this, "396fa9633059eaae5cb3921f58028d29")
    }
}