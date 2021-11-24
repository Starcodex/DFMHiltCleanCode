package com.mfcc.hilt.core.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mfcc.hilt.core.R
import com.mfcc.hilt.core.extensions.inTransaction

abstract class BaseBindingFragmentActivity<B : ViewDataBinding> : BaseBindingActivity<B>() {

    override val layoutResource: Int
        get() = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResource)
        binding.lifecycleOwner = this
        addFragment(savedInstanceState)
    }


    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseBindingFragment<*>).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer,
                fragment()
            )
        }

    abstract fun fragment(): BaseBindingFragment<*>
}