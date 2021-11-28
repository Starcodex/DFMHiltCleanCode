package com.mfcc.hilt.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Either.*
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Failure.*
import com.mfcc.hilt.core.common.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import java.lang.Exception

abstract class BaseRepository {


    fun <T, A> performGetOperation(
        databaseQuery: () -> LiveData<T>,
        networkCall: suspend () -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }

    suspend fun <DB, R, D> performGetRemoteDBFunction(
        databaseQuery: () -> DB,
        networkCall: suspend () -> Call<R>,
        wrapperFunction: suspend (DB) -> D,
        saveCallResult: suspend (R) -> Unit
    ): Either<Failure, D>
    {
        var source = databaseQuery.invoke()
        try {

            val response = networkCall.invoke().execute()
            when (response.isSuccessful) {
                true -> {
                    response.body()?.let { saveCallResult(it) }
                    source = databaseQuery.invoke()
                    Right(wrapperFunction((source)))
                }
                false -> Left(ServerError(code = response.code(), message = response.message()))
            }
        } catch (exception: Throwable) {
            source = databaseQuery.invoke()
            Right(wrapperFunction((source)))
        }

        return Right(wrapperFunction((source)))
    }


    /*{
        try {
            var source = databaseQuery.invoke()
            val response = networkCall.invoke().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    saveCallResult(it)
                    source = databaseQuery.invoke()
                }
            } else {
                return Left(ServerError())
            }
            return Right(wrapperFunction(source))
        } catch (exception: Throwable) {
            return Left(
                WrapperToDomainError(
                    message = exception.message ?: exception.localizedMessage
                )
            )
        }
    }
*/


    suspend fun <DB, R, D> performGet(databaseQuery: () -> DB,
                                      networkCall: suspend () -> Resource<R>,
                                      wrapperFunction: suspend (DB) -> D,
                                      saveCallResult: suspend (R) -> Unit): Either<Failure, D>

        {
            val source = databaseQuery.invoke()
            try{
                val response = networkCall.invoke()
                if(response.status == Resource.Status.SUCCESS){
                    saveCallResult(response.data!!)
                }else{
                    return Left(ServerError())
                }
            }catch(e : Exception){
                return Right(wrapperFunction(source))
            }
            return Right(wrapperFunction(source))
        }

        /*
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }
    */

}