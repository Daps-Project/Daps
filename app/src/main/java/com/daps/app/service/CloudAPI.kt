package com.daps.app.service

import com.daps.app.model.GetQuestionsResponse
import com.daps.app.model.PostResponses
import retrofit2.http.*
import retrofit2.Call

interface CloudAPI {
    @GET("/data")
    suspend fun getQuestions(): GetQuestionsResponse

    @POST("/survey_data")
    fun postData(@Body survey_data: PostResponses): Call<PostResponses>
}