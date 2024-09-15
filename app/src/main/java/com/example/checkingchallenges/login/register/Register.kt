package com.example.checkingchallenges.login.register

import RegisterViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.checkingchallenges.R
import com.example.checkingchallenges.data.database.AppDatabase
import com.example.checkingchallenges.data.database.entities.User
import com.example.checkingchallenges.repository.RepositoryUser


class Register : AppCompatActivity() {

    private lateinit var repositoryUser: RepositoryUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val userDao = AppDatabase.getDatabase(this).userDao()
        repositoryUser = RepositoryUser(userDao)

        val fullName = findViewById<EditText>(R.id.fullName)
        val emailClient = findViewById<EditText>(R.id.emailClient)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)

        findViewById<Button>(R.id.registerMe).setOnClickListener {
            val fullNameText = fullName.text.toString().trim()
            val email = emailClient.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmPasswordText = confirmPassword.text.toString().trim()

            if (passwordText == confirmPasswordText) {
                val user = User(
                    fullName = fullNameText,
                    email = email,
                    password = passwordText,
                    confirmPassword = confirmPasswordText
                )

            } else {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
