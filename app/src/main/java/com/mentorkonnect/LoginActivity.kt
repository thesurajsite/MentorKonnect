package com.mentorkonnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email_et=findViewById<EditText>(R.id.email_et)
        val password_et=findViewById<EditText>(R.id.password_et)
        val checkbox=findViewById<CheckBox>(R.id.checkbox)
        val login_btn=findViewById<Button>(R.id.login_btn)
        val signup_btn=findViewById<Button>(R.id.login_to_signup_btn)

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                password_et.transformationMethod=null
            }
            else{
                password_et.transformationMethod= PasswordTransformationMethod.getInstance()

            }
        }

        signup_btn.setOnClickListener {
            val intent=Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            val email=email_et.text.toString()
            val password=password_et.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty())
            {
                MainActivity.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
        }


    }
}