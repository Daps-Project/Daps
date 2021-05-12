package com.daps.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*class Adapter(private val exampleList: List<Option>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // xml-> view objects
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewcard,
        parent, false)

        return ViewHolder(itemView)
    }

    // updating an item with new data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.question
        holder.textView2.text = currentItem.choice1
        holder.textView3.text = currentItem.choice2
    }

    //how many items in list
    override fun getItemCount() = exampleList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1 : TextView = itemView.findViewById(R.id.text_view_1)
        val textView2 : TextView = itemView.findViewById(R.id.text_view_2)
        val textView3 : TextView = itemView.findViewById(R.id.text_view_3)
    }
}
 */