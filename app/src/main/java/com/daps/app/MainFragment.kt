package com.daps.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// import android.widget.Toast
import androidx.fragment.app.Fragment
// import com.daps.app.model.GetQuestionsResponse
// import com.daps.app.model.Question
//import com.daps.app.service.CloudAPI
// import com.daps.app.service.ServiceBuilder
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.GlobalScope
// import kotlinx.coroutines.launch
//import okhttp3.OkHttpClient
// import retrofit2.Call
// import retrofit2.Callback
// import retrofit2.Response
// import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(R.layout.main_fragment) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val request = retrofit.create(CloudAPI::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call = request.getQuestions()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}