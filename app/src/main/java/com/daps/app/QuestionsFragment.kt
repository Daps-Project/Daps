package com.daps.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.daps.app.service.CloudAPI
import com.daps.app.view.QuestionsAdapter
import kotlinx.android.synthetic.main.questions_fragment_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsFragment : Fragment(R.layout.questions_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val request = retrofit.create(CloudAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val call = request.getQuestions()
            withContext(Dispatchers.Main) {
                questions_viewPager.adapter = QuestionsAdapter(call.question) {
                    questions_viewPager.currentItem = it
                }
            }
        }

    }

}