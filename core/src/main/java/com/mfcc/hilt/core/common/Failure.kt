package com.mfcc.hilt.core.common

sealed class Failure(code: Int = 0, message: String = "Default Error") {
    object NetworkConnection : Failure()
    class ServerError(code: Int = 505, message: String = "Error retrieving data from server") : Failure(code,message)
    class WrapperToDomainError(code: Int = 505, message: String = "Error while wrapping to domain") : Failure(code,message)

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}