package com.daps.app.service

import com.daps.app.model.GetQuestionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CloudAPI {
    @GET("/data")
    suspend fun getQuestions(): GetQuestionsResponse

    @POST("/survey_data")
    fun postData(@Body survey_data: List<String>)
}