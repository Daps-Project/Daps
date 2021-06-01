package com.daps.app.service

import com.daps.app.model.GetQuestionsResponse
import com.daps.app.model.PostResponses
import retrofit2.http.*
import retrofit2.Call
import java.util.*

interface CloudAPI {
    @GET("/data")
    suspend fun getQuestions(): GetQuestionsResponse

    @GET("/survey_data")
    suspend fun getPlaceIDs(): List<String>


    @Headers("Content-Type: application/json")
    @POST("/survey_data")
    fun postData(@Body survey_data: PostResponses): Call<PostResponses>
}