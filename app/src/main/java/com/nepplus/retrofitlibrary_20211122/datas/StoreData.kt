package com.nepplus.retrofitlibrary_20211122.datas

import com.google.gson.annotations.SerializedName

class StoreData(
    var id: Int,
    var name: String,
    @SerializedName("logo_url")
    var logoURL : String,



    ) {
}