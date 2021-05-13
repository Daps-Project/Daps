package com.daps.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.daps.app.model.Option
import com.daps.app.model.Question
import com.daps.app.service.CloudAPI
import com.daps.app.view.QuestionsAdapter
import kotlinx.android.synthetic.main.answer_layout.view.*
import kotlinx.android.synthetic.main.questions_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsFragment : Fragment(R.layout.questions_fragment) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val request = retrofit.create(CloudAPI::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = request.getQuestions()
            QuestionsAdapter(call.question)
        }
    }

}