package com.example.checkingchallenges.Login.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.checkingchallenges.Login.LoginUser
import com.example.checkingchallenges.R

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val fullName = findViewById<TextView>(R.id.fullName)
        val emailClient = findViewById<TextView>(R.id.emailClient)
        val password = findViewById<TextView>(R.id.password)
        val confirmPassword = findViewById<TextView>(R.id.confirmPassword)

        findViewById<Button>(R.id.registerMe).setOnClickListener {
            startActivity(Intent(this, LoginUser::class.java))



            }
        }
    }

