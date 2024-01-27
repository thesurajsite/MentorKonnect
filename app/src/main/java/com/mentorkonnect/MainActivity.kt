package com.mentorkonnect

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth=FirebaseAuth.getInstance()
        if(auth.currentUser==null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val home_button=findViewById<ImageView>(R.id.home_button)
        val add_button=findViewById<ImageView>(R.id.add_button)
        val profile_button=findViewById<ImageView>(R.id.profile_button)




        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, home())
//        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


        home_button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, home())
//            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


        profile_button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, mentor_profile())
            //fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        add_button.setOnClickListener {
//            val fragmentTransaction = supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragmentContainer, create_post_mentee())
//            //fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.create_post_layout)
            dialog.show()

        }

    }
}