package com.nepplus.retrofitlibrary_20211122.api

import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServerAPIServiceInterface {
//    로그인 기능
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
    @field("email") email:String,
    @field("password") pw:String)
}