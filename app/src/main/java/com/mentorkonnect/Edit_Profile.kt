package com.mentorkonnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Edit_Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val userName=findViewById<EditText>(R.id.user_Name)
        val userBio=findViewById<EditText>(R.id.userBio)
        val saveChanges=findViewById<Button>(R.id.saveChanges)



    }
}