package com.nepplus.retrofitlibrary_20211122.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nepplus.retrofitlibrary_20211122.pregments.MyProfilePregment
import com.nepplus.retrofitlibrary_20211122.pregments.ProductListPregment
import com.nepplus.retrofitlibrary_20211122.pregments.ReviewListPregment

class MainViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount()=3

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "리뷰목록"
            1-> "상품목록"
            else->"내 프로필"
        }
    }

    override fun getItem(position: Int): Fragment {


       return when(position){

           0-> ReviewListPregment()
           1-> ProductListPregment()
           else -> MyProfilePregment()
       }
    }
}