package com.daps.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.daps.app.service.CloudAPI
import com.daps.app.view.QuestionsAdapter
import kotlinx.android.synthetic.main.answer_layout.view.*
import kotlinx.android.synthetic.main.questions_fragment_layout.*
import kotlinx.android.synthetic.main.questions_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsFragment : Fragment(R.layout.questions_fragment_layout) {

    private lateinit var viewPager: ViewPager2

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
            viewPager.adapter = QuestionsAdapter(call.question)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = questions_viewPager


    }

}