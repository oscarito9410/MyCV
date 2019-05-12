package com.booleansystems.data.common

/**
Created by oscar on 10/05/19
Notify is response is success or not */
interface IBaseResultListener<T> {
    fun onSuccess(response: T);
    fun onError(error: Throwable)
}