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
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class home : Fragment() {

//    private val dbref: DatabaseReference
    private val db: FirebaseFirestore by lazy { Firebase.firestore}
    private lateinit var feedRecyclerView: RecyclerView
    private lateinit var PostList: ArrayList<MentorPostModel>

    private val USER= MainActivity.auth.currentUser?.uid.toString()
    private val USER_INFO="UserInfo"
    private val NAME="name"
    private val BIO="bio"
    private val POST_CONTENT="post_content"
    private val USER_ID="userID"
    private val TIME="time"
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

        val floatingActionButton=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val progressBar=view.findViewById<ProgressBar>(R.id.Progressbar)


        PostList= arrayListOf<MentorPostModel>()
        feedRecyclerView = view.findViewById<RecyclerView>(R.id.feedRecyclerView)
        feedRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        feedRecyclerView.setHasFixedSize(true)

        getPostData()

        val feedRecyclerAdapter = RecyclerFeedAdapter2(PostList)
        feedRecyclerView.adapter = feedRecyclerAdapter


        // Update the RecyclerView adapter with the new posts
        feedRecyclerAdapter.notifyDataSetChanged()


//        arrPosts.add(MentorPostModel(R.drawable.mentor_profile_image,"Sunny Kumar","Sir, can you please tell me what is more important for placements: DSA or Development ? Someone says DSA is more important, someone says Development. I am so confused, please mentor me."))
//        arrPosts.add(MentorPostModel(R.drawable.profile_image,"Suraj Verma","Hey There, welcome to the MentorKonnect App, This is Suraj Verma here and if you have any problem and need my help, just create a post and tag me, i will try my best to Help You"))


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

                    val time=getCurrentTimeString()
                    val map= mutableMapOf<String, String>()
                    map.put(NAME, name)
                    map.put(POST_CONTENT, postContent_String)
                    map.put(USER_ID, USER)
                    map.put(TIME, time)

//                    Toast.makeText(context, time, Toast.LENGTH_SHORT).show()

                    db.collection(USER_POSTS).document().set(map)
                        .addOnSuccessListener {
                            progressBar.visibility=ProgressBar.INVISIBLE
                            Toast.makeText(context, "Post Created Successfully", Toast.LENGTH_SHORT).show()

                            //RECYCLER VIEW
                            arrPosts.add(MentorPostModel(name,postContent_String))
                            feedRecyclerAdapter.notifyDataSetChanged()
//                            feedRecyclerAdapter.notifyItemChanged(arrPosts.size-1)
                            feedRecyclerView.scrollToPosition(arrPosts.size-1)
                            dialog.dismiss()

                            // TO REFRESH THE RECYCLER VIEW
                            getPostData()

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

    private fun getPostData() {
        val reference = db.collection(USER_POSTS)

        reference.orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { snapshot ->
                // Clear the existing post list
                PostList.clear()

                // Process the snapshot to get the new posts
                for (document in snapshot.documents) {
                    val UserName = document.getString(NAME) ?: ""
                    val postContent = document.getString(POST_CONTENT) ?: ""

                    // Create a MentorPostModel and add it to the list
                    val mentorPostModel = MentorPostModel(UserName, postContent)
                    PostList.add(mentorPostModel)
                }

                // Notify the adapter that the data has changed
                feedRecyclerView.adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { error ->
                Toast.makeText(context, "Error fetching posts: $error", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getCurrentTimeString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = Date(System.currentTimeMillis())
        return dateFormat.format(currentTime)
    }

}

