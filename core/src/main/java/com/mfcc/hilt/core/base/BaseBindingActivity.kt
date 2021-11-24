package com.mfcc.hilt.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseBindingActivity<B : ViewDataBinding> : BaseActivity() {

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResource)
        binding.lifecycleOwner = this
    }

}