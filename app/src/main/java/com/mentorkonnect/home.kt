package com.mentorkonnect

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class home : Fragment() {

    private val db: FirebaseFirestore by lazy { Firebase.firestore}

    private val USER= MainActivity.auth.currentUser?.uid.toString()
    private val USER_INFO="UserInfo"
    private val NAME="name"
    private val BIO="bio"
    private val POST_CONTENT="post_content"
    private val USER_ID="userID"
    private val USER_POSTS="userPosts"
    private lateinit var name: String


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
        val progressBar=view.findViewById<ProgressBar>(R.id.Progressbar)


//        arrPosts.add(MentorPostModel(R.drawable.mentor_profile_image,"Sunny Kumar","Sir, can you please tell me what is more important for placements: DSA or Development ? Someone says DSA is more important, someone says Development. I am so confused, please mentor me."))
//        arrPosts.add(MentorPostModel(R.drawable.profile_image,"Suraj Verma","Hey There, welcome to the MentorKonnect App, This is Suraj Verma here and if you have any problem and need my help, just create a post and tag me, i will try my best to Help You"))

        feedRecyclerView.adapter = feedRecyclerAdapter
        feedRecyclerView.layoutManager= LinearLayoutManager(requireContext())

        floatingActionButton.setOnClickListener {
            val dialog=Dialog(requireContext())
            dialog.setContentView(R.layout.create_post_layout)
            dialog.show()

            val createPost_btn=dialog.findViewById<Button>(R.id.createPost_btn)

            createPost_btn.setOnClickListener {

                progressBar.visibility=ProgressBar.VISIBLE
                val postContent=dialog.findViewById<TextView>(R.id.PostContent)
                val postContent_String:String=postContent.text.toString()


                // SAVING POST DATA TO THE FIREBASE //
                if(postContent_String.isNotEmpty())
                {
                    val map= mutableMapOf<String, String>()
                    map.put(NAME, name)
                    map.put(POST_CONTENT, postContent_String)
                    map.put(USER_ID, USER)

                    db.collection(USER_POSTS).document().set(map)
                        .addOnSuccessListener {
                            progressBar.visibility=ProgressBar.INVISIBLE
                            Toast.makeText(context, "Post Created Successfully", Toast.LENGTH_SHORT).show()

                            //RECYCLER VIEW
                            arrPosts.add(MentorPostModel(R.drawable.profile_image,"Suraj Verma",postContent_String))
                            feedRecyclerAdapter.notifyItemChanged(arrPosts.size-1)
                            feedRecyclerView.scrollToPosition(arrPosts.size-1)
                            dialog.dismiss()

                        }
                        .addOnFailureListener {
                            progressBar.visibility=ProgressBar.INVISIBLE
                            Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                        }

                }






            }


            // GETTING USER NAME FROM THE DATABASE AS SOON AS THE FAB IS CLICKED
            db.collection(USER_INFO).document(USER).get()
                .addOnSuccessListener {
                    name=it.get(NAME).toString()

                }
                .addOnFailureListener {

                    Toast.makeText(context, "Some Error Occured", Toast.LENGTH_SHORT).show()

                }



        }





        return view
    }

}

