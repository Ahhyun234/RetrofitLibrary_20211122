package com.nepplus.retrofitlibrary_20211122.pregments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nepplus.retrofitlibrary_20211122.R
import com.nepplus.retrofitlibrary_20211122.databinding.ShopListFragmentBinding

class ShopListPregment : Fragment() {

    lateinit var binding:ShopListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.shop_list_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}