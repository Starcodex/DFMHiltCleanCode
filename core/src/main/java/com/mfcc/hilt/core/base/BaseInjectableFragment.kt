package com.mfcc.hilt.core.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfcc.hilt.core.di.ViewModelFactory
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseInjectableFragment<VM: ViewModel, B: ViewDataBinding> : BaseBindingFragment<B>() {


    @Inject
    lateinit var factory: ViewModelFactory

    lateinit var viewModel: VM


    override fun onAttach(context: Context) {
        //AndroidSupportInjection.inject(this)
        injection()
        super.onAttach(context)
        generateViewModel()
    }

    fun generateViewModel(){
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        viewModel = ViewModelProvider(this, factory).get(viewModelClass)
    }


    abstract fun injection()

}