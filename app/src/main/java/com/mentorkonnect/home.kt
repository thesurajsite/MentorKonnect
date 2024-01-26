package com.mentorkonnect

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

class home : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        val arrPosts=ArrayList<MentorPostModel>()
        val arrReply=ArrayList<ReplyPostModel>()
        val feedRecyclerView = view.findViewById<RecyclerView>(R.id.feedRecyclerView)
        val feedRecyclerAdapter = RecyclerFeedAdapter(requireContext(), arrPosts, arrReply)
        val floatingActionButton=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)


        arrPosts.add(MentorPostModel(R.drawable.mentor_profile_image,"Sunny Kumar","Sir, can you please tell me what is more important for placements: DSA or Development ? Someone says DSA is more important, someone says Development. I am so confused, please mentor me."))
        arrPosts.add(MentorPostModel(R.drawable.profile_image,"Suraj Verma","Hey There, welcome to the MentorKonnect App, This is Suraj Verma here and if you have any problem and need my help, just create a post and tag me, i will try my best to Help You"))

        feedRecyclerView.adapter = feedRecyclerAdapter
        feedRecyclerView.layoutManager= LinearLayoutManager(requireContext())

        floatingActionButton.setOnClickListener {
            val dialog=Dialog(requireContext())
            dialog.setContentView(R.layout.create_post_layout)
            dialog.show()

            val createPost_btn=dialog.findViewById<Button>(R.id.createPost_btn)

            createPost_btn.setOnClickListener {

                val postContent=dialog.findViewById<TextView>(R.id.PostContent)
                val postContent_String:String=postContent.text.toString()

                arrPosts.add(MentorPostModel(R.drawable.profile_image,"Suraj Verma",postContent_String))
                feedRecyclerAdapter.notifyItemChanged(arrPosts.size-1)
                feedRecyclerView.scrollToPosition(arrPosts.size-1)
                dialog.dismiss()
            }


        }





        return view
    }

}

