package com.nepplus.retrofitlibrary_20211122.api

import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface ServerAPIServiceInterface {

    //    로그인 기능
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    )
            : Call<BasicResponse>

    //     회원가입 기능 명세
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nickname: String
    ): Call<BasicResponse>

//    중복 확인 기능

    @GET("/user/check")
    fun getRequestDuplicatedCheck(

        @Query("type") type: String,
        @Query("value") value: String,

        ): Call<BasicResponse>

//    소셜 로그인
    @FormUrlEncoded
    @POST("/user/social")
    fun postRequestSocialLogin(
    @Field("provider") provider: String,
    @Field("uid") uid: String,
    @Field("nick_name") nick_name:String,
    ): Call<BasicResponse>


//    내 정보 조회 / get방식 / 토큰 필요 (임시 방안)

    @GET("/user")
    fun getRequestMyinfo(
        @Header("X-Http-Token") token: String
    ): Call<BasicResponse>
}