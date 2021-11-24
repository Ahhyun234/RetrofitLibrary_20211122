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
import com.nepplus.retrofitlibrary_20211122.databinding.ProductListFragmentBinding
import com.nepplus.retrofitlibrary_20211122.databinding.ReviewListPregmentListBinding

class ProductListPregment : Fragment() {

    lateinit var binding: ProductListFragmentBinding

    override fun onCreateView(    //xml을 끌어와주는 함수
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.product_list_fragment,container,false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}