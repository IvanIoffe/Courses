package com.ioffeivan.courses.core.network

import com.google.gson.Gson
import com.ioffeivan.courses.core.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response

inline fun <reified T> remoteRequestFlow(
    crossinline call: suspend () -> Response<ResponseBody>,
): Flow<Result<T>> =
    flow {
        emit(Result.Loading)

        try {
            val response = call()

            val gson = Gson()
            if (response.isSuccessful) {
                response.body()?.string()?.let { data ->
                    val result = gson.fromJson(data, T::class.java)
                    emit(Result.Success(result))
                }
            } else {
                /*response.errorBody()?.let { error ->
                    val parsedError = gson.fromJson(error.string())
                    emit(Result.Error(parsedError.message))
                }*/
            }
        } catch (e: Exception) {
            emit(Result.Error("Ошибка подключения. Повторите попытку позже."))
        }

    }.flowOn(Dispatchers.IO)