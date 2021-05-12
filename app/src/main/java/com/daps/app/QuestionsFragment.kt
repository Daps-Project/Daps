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

    class QuestionsAdapter(call : Question) : RecyclerView.Adapter<QuestionsViewHolder>() {
        val questionList: List<Question> = mutableListOf(call)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
            return QuestionsViewHolder(parent)
        }

        override fun getItemCount(): Int = questionList.size

        override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
            holder.bind(questionList[position])
        }
    }

    class QuestionsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(LayoutInflater.from(parent.context).inflate(R.layout.questions_layout, parent, false))
        fun bind(question: Question){
            itemView.questions_text.text = question.text
            OptionsAdapter(question.options)
        }
    }

    class OptionsAdapter(options: List<Option>) : RecyclerView.Adapter<OptionsViewHolder>(){
        val optionList = options
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
            return OptionsViewHolder(parent)
        }

        override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
            holder.bind(optionList[position])
        }

        override fun getItemCount(): Int = optionList.size

    }
    class OptionsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(LayoutInflater.from(parent.context).inflate(R.layout.answer_layout, parent, false))

        fun bind(option: Option){
            itemView.answers
        }
    }

}