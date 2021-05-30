package com.daps.app

import com.daps.app.model.GetQuestionsResponse
import com.daps.app.model.PostResponses
import com.daps.app.service.CloudAPI
import com.daps.app.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RestApiService {
    private val request = ServiceBuilder.buildService(CloudAPI::class.java)

    fun sendData(data: PostResponses, onResult: (PostResponses?) -> Unit) {

        request.postData(data).enqueue(
            object : Callback<PostResponses> {
                override fun onFailure(call: Call<PostResponses>, t: Throwable) {
                    onResult(data)
                }
                override fun onResponse(call: Call<PostResponses>, response: Response<PostResponses>) {
                    val postedData = response.body()
                    onResult(postedData)
                }
            }
        )
    }
    suspend fun getQuestions(): GetQuestionsResponse {
        return request.getQuestions()
    }

    suspend fun getPlaceIDs(): Dictionary<Int, String> {
        return request.getPlaceIDs()
    }
}