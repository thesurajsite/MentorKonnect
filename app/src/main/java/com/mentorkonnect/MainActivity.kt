package com.mentorkonnect

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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