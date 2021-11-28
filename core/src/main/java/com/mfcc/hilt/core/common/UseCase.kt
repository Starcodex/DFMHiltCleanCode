package com.mfcc.hilt.core.common

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

abstract class UseCase<Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    abstract fun exec(params: Params): LiveData<Resource<Type>>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())
        }
    }



    class None

}