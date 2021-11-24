package com.nepplus.retrofitlibrary_20211122.datas

import com.google.gson.annotations.SerializedName

class ProductData(

    var id : Int,
    var name : String,

    var store: StoreData,
    @SerializedName("small_category")
    var smallcategory : SmallcategoryData


) {
}