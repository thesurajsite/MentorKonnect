package com.mentorkonnect

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
class RecyclerFeedAdapter(val context: Context,
                          val arrPosts: ArrayList<MentorPostModel>,
                            val arrReply: ArrayList<ReplyPostModel>
                            ) : RecyclerView.Adapter<RecyclerFeedAdapter.ViewHolder>() {

    private val VIEW_TYPE_POST = 1
    private val VIEW_TYPE_REPLY = 2

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val ProfileImage=itemView.findViewById<ImageView>(R.id.ProfileImage)
        val UserName = itemView.findViewById<TextView>(R.id.UserName)
        val PostContent=itemView.findViewById<TextView>(R.id.PostContent)
        val LikeButton=itemView.findViewById<Button>(R.id.LikeButton)
        val RepostButton=itemView.findViewById<Button>(R.id.RepostButton)
        val LikeCount=itemView.findViewById<TextView>(R.id.LikeCount)
        val RepostCount=itemView.findViewById<TextView>(R.id.RepostCount)
        val HelpButton=itemView.findViewById<Button>(R.id.HelpButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.mentee_post_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return arrPosts.size


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //holder.ProfileImage.setImageResource(arrPosts[position].profileImage)
        holder.UserName.text=arrPosts[position].UserName
        holder.PostContent.text=arrPosts[position].PostContent
        
        holder.HelpButton.setOnClickListener {

            val dialog=Dialog(context)
            dialog.setContentView(R.layout.reply_post_layout)
            dialog.show()

            val ReplyPostContent=dialog.findViewById<EditText>(R.id.ReplyPostContent)
            val ReplyButton=dialog.findViewById<Button>(R.id.Reply_btn)
            val ReplyPostContent_String=ReplyPostContent.text.toString()

            var profileImage1=R.drawable.profile_image
            var UserName1="Suraj Verma"
            var PostContent1=ReplyPostContent_String
           // var ProfileImage2=arrPosts[position].profileImage
            var UserName2= arrPosts[position].UserName
            var PostContent2=arrPosts[position].PostContent

           // arrReply.add(ReplyPostModel(profileImage1,UserName1,PostContent1,ProfileImage2,UserName2,PostContent2))



        }




    }

}
