package com.daps.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.emoji.text.EmojiCompat
import androidx.recyclerview.widget.RecyclerView
import com.daps.app.R
import com.daps.app.model.Option
import com.daps.app.model.Question
import kotlinx.android.synthetic.main.answer_layout.view.*

class OptionsAdapter(options: List<Option>, val callback: (Question?, String) -> Unit) :
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {
    private val optionList = options

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        return OptionsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bind(optionList[position])
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    inner class OptionsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.answer_layout, parent, false)
                )

        fun bind(option: Option) {
            itemView.answer_text.text = EmojiCompat
                .get()
                .process("${option.text}") /* \uD83D\uDC4A") */

            itemView.answer_text.setOnClickListener {
                callback(option.question, option.text)
            }

        }
    }
}
