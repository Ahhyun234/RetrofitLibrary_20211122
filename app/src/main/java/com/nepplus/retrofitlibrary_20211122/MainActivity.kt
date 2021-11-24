package com.nepplus.retrofitlibrary_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.retrofitlibrary_20211122.adapters.MainViewPagerAdapter
import com.nepplus.retrofitlibrary_20211122.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mMainViewPagerAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mMainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewPager.adapter=mMainViewPagerAdapter

    }
}