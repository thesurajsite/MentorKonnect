package com.mentorkonnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerFeedAdapter2(private val PostList:ArrayList<MentorPostModel>):RecyclerView.Adapter<RecyclerFeedAdapter2.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.mentee_post_layout, parent, false)
        return MyViewHolder(itemView
        )
    }

    override fun getItemCount(): Int {
        return PostList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=PostList[position]

        holder.UserName.text=currentItem.UserName
        holder.PostContent.text=currentItem.PostContent
    }

    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){

        val UserName = itemView.findViewById<TextView>(R.id.UserName)
        val PostContent=itemView.findViewById<TextView>(R.id.PostContent)

    }
}