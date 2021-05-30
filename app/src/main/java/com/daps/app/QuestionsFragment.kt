package com.daps.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daps.app.model.PostResponses
import com.daps.app.view.QuestionsAdapter
import kotlinx.android.synthetic.main.questions_fragment_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuestionsFragment : Fragment(R.layout.questions_fragment_layout) {

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            val call = RestApiService().getQuestions()
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


