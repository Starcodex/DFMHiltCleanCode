package com.mfcc.hilt.core.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseInjectableActivity<VM: ViewModel, B: ViewDataBinding> : BaseBindingActivity<B>() {

    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        viewModel = ViewModelProvider(this).get(viewModelClass)
    }

}