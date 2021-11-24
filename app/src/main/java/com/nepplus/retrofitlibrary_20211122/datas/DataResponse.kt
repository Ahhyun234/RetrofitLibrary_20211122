package com.nepplus.retrofitlibrary_20211122.datas

class DataResponse(
    var token : String,
    var user : UserData,

//    아래 변수는 상품목록에서만 사용 할 예정
    var product : List<ProductData>,


) {


}