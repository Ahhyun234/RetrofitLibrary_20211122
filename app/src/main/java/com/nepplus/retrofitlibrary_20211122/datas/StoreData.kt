package com.nepplus.retrofitlibrary_20211122.datas

class StoreData(

    var id : Int,
    var name : String,

    @SerializeName ("logoUrl")
    var logoUrl =
) {
}