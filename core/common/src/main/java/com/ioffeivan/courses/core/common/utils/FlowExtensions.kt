package com.ioffeivan.courses.core.common.utils

import com.ioffeivan.courses.core.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
    return merge(this, another)
}

fun <T> Flow<T>.toFlowResult(): Flow<Result<T>> = map { Result.Success(it) }
    .onStart { Result.Loading }
    .catch { Result.Error(it.message.orEmpty()) }