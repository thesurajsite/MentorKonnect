package com.mentorkonnect

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class mentor_profile : Fragment() {

    private val db: FirebaseFirestore by lazy { Firebase.firestore}
    private val USER= MainActivity.auth.currentUser?.uid.toString()
    private val USER_INFO="UserInfo"
    private val NAME="name"
    private val BIO="bio"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mentor_profile, container, false)

        var userName=view.findViewById<TextView>(R.id.userName)
        val userBio=view.findViewById<TextView>(R.id.userBio)
        val logout_btn=view.findViewById<Button>(R.id.logout_btn)
        val edit_profile=view.findViewById<Button>(R.id.edit_profile_btn)
        val progressBar=view.findViewById<ProgressBar>(R.id.Progressbar)


        logout_btn.setOnClickListener {
            MainActivity.auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()

        }

        edit_profile.setOnClickListener {
            startActivity(Intent(context, Edit_Profile::class.java))
        }

        progressBar.visibility= ProgressBar.VISIBLE
        db.collection(USER_INFO).document(USER).get()
            .addOnSuccessListener {
                val name=it.get(NAME).toString()
                val bio=it.get(BIO).toString()

                userName.setText(name)
                userBio.setText(bio)

                progressBar.visibility= ProgressBar.INVISIBLE

            }
            .addOnFailureListener {
                userName.setText("Anonymous")
                userBio.setText("No Bio")
                //Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show()
                progressBar.visibility= ProgressBar.INVISIBLE


            }







        return view
    }


}