package com.mfcc.hilt.core.base

abstract class BaseWrapper<A,B>() {
    abstract fun wrapElement(inVar : A) : B
}