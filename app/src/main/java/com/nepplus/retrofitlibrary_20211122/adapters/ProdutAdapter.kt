package com.nepplus.retrofitlibrary_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nepplus.retrofitlibrary_20211122.R
import com.nepplus.retrofitlibrary_20211122.datas.ProductData

class ProdutAdapter(val mComtext: Context, resId:Int , val mList: List<ProductData>):ArrayAdapter<ProductData>(mComtext,resId,mList) {

    val mInflater = LayoutInflater.from(mComtext)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow==null){
            tempRow = mInflater.inflate(R.layout.fragment_product_list_item,null)

        }
        val row = tempRow!!

        val data = mList[position]
        val txtProductName = row.findViewById<TextView>(R.id.txtProductName)
        val imgStoreLogo = row.findViewById<ImageView>(R.id.imgStoreLogo)
        val txtStoreName = row.findViewById<TextView>(R.id.txtStoreName)

        txtProductName.text = data.name
        txtStoreName.text = data.store.name

        Glide.with(mComtext).load(data.store.logoUrl).into(imgStoreLogo)


        return row
    }

}