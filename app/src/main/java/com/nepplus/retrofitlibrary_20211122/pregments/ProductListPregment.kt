package com.nepplus.retrofitlibrary_20211122.pregments

import android.os.Bundle
import android.util.Log
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
import com.nepplus.retrofitlibrary_20211122.datas.BasicResponse
import com.nepplus.retrofitlibrary_20211122.datas.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListPregment : BaseFragment() {

    lateinit var binding: ProductListFragmentBinding
    val mProductList = ArrayList<ProductData>()

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

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {


        getProductListFromServer()
    }

    fun getProductListFromServer(){

        apiService.getRequestProductList().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    val br = response.body()!!

                    mProductList.clear()
                    mProductList.addAll(br.data.product)


                    for(product in br.data.product){
                        Log.d("상품명", product.name )
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}