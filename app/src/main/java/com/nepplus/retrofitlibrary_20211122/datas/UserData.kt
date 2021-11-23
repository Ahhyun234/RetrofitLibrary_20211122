package com.nepplus.retrofitlibrary_20211122.datas

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

class UserData(
    var id: Int,
    var email : String,
//     서버의 이름표와 내가 저장하고싶은 이름이 다를 때
    @SerializedName("nick_name")
    var nickname: String,

    @SerializedName("profile_img")
    var profileImageUrl : String

) {
}