package com.ioffeivan.courses.core.network.api_service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CourseApiService {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): Response<ResponseBody>
}