package com.daps.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.daps.app.model.PostResponses
import com.daps.app.service.CloudAPI
import com.daps.app.service.ServiceBuilder
import com.daps.app.view.QuestionsAdapter
import kotlinx.android.synthetic.main.questions_fragment_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionsFragment : Fragment(R.layout.questions_fragment_layout) {

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = ServiceBuilder.buildService(CloudAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val call = request.getQuestions()
            withContext(Dispatchers.Main) {
                questions_viewPager.adapter = QuestionsAdapter(
                    call.question,
                    {
                        questions_viewPager.currentItem = it
                    },
                    {
                        GlobalScope.launch(Dispatchers.IO) {
                            val apiService = RestApiService()
                            val postRequest = PostResponses(responses = it)
                            apiService.sendData(postRequest) {
                                Log.v(
                                    "Logging this response back",
                                    it?.responses.toString()
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}
class RestApiService {
    fun sendData(data: PostResponses, onResult: (PostResponses?) -> Unit) {
        val postRequest = ServiceBuilder.buildService(CloudAPI::class.java)
        postRequest.postData(data).enqueue(
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
}

