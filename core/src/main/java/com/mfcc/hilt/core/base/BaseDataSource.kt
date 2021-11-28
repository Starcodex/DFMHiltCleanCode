package com.mfcc.hilt.core.base

import android.util.Log
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Either.*
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Failure.*
import com.mfcc.hilt.core.common.Resource
import com.mfcc.hilt.core.di.NetworkHandler
import retrofit2.Call
import retrofit2.Response

abstract class BaseDataSource (
    private val networkHandler: NetworkHandler
) {

    protected fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T)
            : Either<Failure, R> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform(response.body() ?: default))
                    false -> Left(ServerError(response.code(), response.message()))
                }
            } catch (exception: Throwable) {
                Left(ServerError(message = exception.message ?: exception.toString()))
            }
            false -> Left(NetworkConnection)
        }
    }


    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        if(networkHandler.isNetworkAvailable()){
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) return Resource.success(body)
                }
                return error(" ${response.code()} ${response.message()}")
            } catch (e: Exception) {
                return error(e.message ?: e.toString())
            }
        }else{
            return error("NetworkConnection")
        }
    }


    private fun <T> error(message: String): Resource<T> {
        Log.e("ERROR IN CALL",message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}