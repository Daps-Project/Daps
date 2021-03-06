package com.daps.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daps.app.R
import com.daps.app.model.Question
import kotlinx.android.synthetic.main.questions_layout.view.*

class QuestionsAdapter(firstQuestion: Question?, val onQuestionSelected: (Int) -> Unit, val onSurveyComplete: (List<String>) -> Unit ) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>() {
    private val questionList = mutableListOf(firstQuestion)
    private val surveyResults = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(parent)
    }

    override fun getItemCount(): Int = questionList.size

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        questionList[position]?.let { holder.bind(it) }
    }

    inner class QuestionsViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.questions_layout, parent, false)
                )

        fun bind(question: Question) {
            itemView.questions_text.text = question.text
            itemView.options_list.adapter = OptionsAdapter(question.options) { it, optionText ->
                surveyResults.add(optionText)
                if (it != null) {
                    questionList.add(it)
                    onQuestionSelected(questionList.size - 1)
                    notifyDataSetChanged()
                }
                else{
                    onSurveyComplete(surveyResults)
                }
            }
        }
    }
}


