package com.nepplus.retrofitlibrary_20211122.pregments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nepplus.retrofitlibrary_20211122.BaseActivity
import com.nepplus.retrofitlibrary_20211122.R
import com.nepplus.retrofitlibrary_20211122.binding
import com.nepplus.retrofitlibrary_20211122.databinding.MyprofileFragmentBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ReviewListPregmentListBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ShopListFragmentBinding

class MyProfilePregment : Fragment() {

    lateinit var binding:MyprofileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.myprofile_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}