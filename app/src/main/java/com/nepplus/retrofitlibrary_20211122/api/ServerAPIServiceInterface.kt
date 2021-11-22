package com.nepplus.retrofitlibrary_20211122.api

import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

interface ServerAPIServiceInterface {

//    로그인 기능
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
    @Field("email") email:String,
    @Field("password") pw:String)
    : Call<BasicResponse>

//     회원가입 기능 명세
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
    @Field("email") email:String,
    @Field("password") pw:String,
    @Field("nick_name") nickname : String): Call<BasicResponse>
}