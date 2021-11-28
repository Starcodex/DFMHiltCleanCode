package com.mfcc.hilt.core.base

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Resource
import com.mfcc.hilt.core.common.Resource.*


abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    // Error Handle
    private val _error: MutableLiveData<Resource<*>> = MutableLiveData()
    val error: LiveData<Resource<*>> = _error

    protected fun <T>handleError(error: Resource<T>) {
        _error.value = error
    }

    // Success Handle


    protected fun <T> handleLiveData(
        liveData: LiveData<Resource<T>>,
        handleSuccess: (Resource<T>) -> Unit, handleError: (Resource<T>) -> Unit
        ) {
        val liveDataMediator = MediatorLiveData<T>()
        liveDataMediator.addSource(liveData, Observer {
            when (it.status) {
                Status.SUCCESS -> { handleSuccess(it) }
                Status.ERROR -> { handleError(it) }
                else -> {}
            }
        })
    }

    /*

    protected fun <T> handleLiveData(
        liveData: LiveData<Resource<T>>,
        fold: ( handleSuccess: (Resource<T>) -> Unit, error: (Resource<T>) -> Unit) -> Unit
        ) {
        val liveDataMediator = MediatorLiveData<T>()
        liveDataMediator.addSource(liveData, Observer {
            when (it.status) {
                Status.SUCCESS -> { fold(hand) }
                Status.ERROR -> { handleError(it) }
                else -> {}
            }
        })
    }

    * */

}