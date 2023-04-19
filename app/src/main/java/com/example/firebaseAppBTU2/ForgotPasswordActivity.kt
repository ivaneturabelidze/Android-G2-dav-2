package com.example.firebaseAppBTU2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity(){
    private var auth = Firebase.auth
    private lateinit var forgotPasswordEmailAddress: EditText
    private lateinit var forgotPasswordButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        listeners()
    }

    private fun listeners(){
        forgotPasswordButton.setOnClickListener{
            val email = forgotPasswordEmailAddress.text.toString()
            if(email.isEmpty()){
                return@setOnClickListener
            }else{
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun init(){
        forgotPasswordEmailAddress = findViewById(R.id.forgotPasswordEmailAddress)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
    }
}