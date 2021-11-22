package com.nepplus.retrofitlibrary_20211122.api

import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServerAPIServiceInterface {

//    로그인 기능
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
    @Field("email") email:String,
    @Field("password") pw:String)
    : Call<BasicResponse>
}