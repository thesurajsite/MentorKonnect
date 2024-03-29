package com.mentorkonnect

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes

class Edit_Profile : AppCompatActivity() {

    private val db: FirebaseFirestore by lazy {Firebase.firestore}

    private val USER= MainActivity.auth.currentUser?.uid.toString()
    private val USER_INFO="UserInfo"
    private val NAME="name"
    private val BIO="bio"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)



        val userName=findViewById<EditText>(R.id.user_Name)
        val userBio=findViewById<EditText>(R.id.userBio)
        val saveChanges=findViewById<Button>(R.id.saveChanges)
        val progressBar=findViewById<ProgressBar>(R.id.Progressbar)


        progressBar.visibility=ProgressBar.VISIBLE
        db.collection(USER_INFO).document(USER).get()
            .addOnSuccessListener {
                val name=it.get(NAME).toString()
                val bio=it.get(BIO).toString()

                userName.setText(name)
                userBio.setText(bio)

                progressBar.visibility=ProgressBar.INVISIBLE
//                Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                userName.setText("Anonymous")
                userBio.setText("No Bio")
                Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show()
                progressBar.visibility=ProgressBar.INVISIBLE


            }


        saveChanges.setOnClickListener {

            progressBar.visibility=ProgressBar.VISIBLE

//            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            var name=userName.text.toString()
            var bio=userBio.text.toString()

            if(name.isNotEmpty())
            {
                val map= mutableMapOf<String, String>()
                map.put(NAME, name)
                map.put(BIO, bio)
                db.collection(USER_INFO).document(USER).set(map)
                    .addOnSuccessListener {
                        progressBar.visibility=ProgressBar.INVISIBLE
                        Toast.makeText(this, "Changes Saved Successfully", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener {
                        progressBar.visibility=ProgressBar.INVISIBLE
                        Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
                    }


            }




        }









    }

}