package com.example.checkingchallenges.Login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkingchallenges.Login.Register.Register
import com.example.checkingchallenges.Login.WelcomeActivity.WelcomeActivity
import com.example.checkingchallenges.R
import com.example.checkingchallenges.repository.RepositoryUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginUser : AppCompatActivity() {

    private lateinit var repositoryUser: RepositoryUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_user)


        repositoryUser = RepositoryUser(applicationContext)

        val emailClient = findViewById<EditText>(R.id.emailClient)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.loginButton)

        button.setOnClickListener {
            val email = emailClient.text.toString().trim()
            val pass = password.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (isValidEmail(email) && isValidPassword(pass)) {

                    validateUserCredentials(email, pass)
                } else {
                    Toast.makeText(this, "Invalid email or password format", Toast.LENGTH_SHORT).show()
                }
            } else {
                emailClient.error = "Email cannot be empty"
                password.error = "Password cannot be empty"
            }
        }

        val textClickable = findViewById<TextView>(R.id.notUser)
        textClickable.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun validateUserCredentials(email: String, password: String) {

        CoroutineScope(Dispatchers.IO).launch {
            repositoryUser.getUsers { users ->
                val user = users.find { it.email == email }
                if (user != null && user.password == password) {

                    runOnUiThread {
                        val intent = Intent(this@LoginUser, WelcomeActivity::class.java)
                        startActivity(intent)
                    }
                } else {

                    runOnUiThread {
                        Toast.makeText(this@LoginUser, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {

        return password.length >= 6
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "This is onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "This is onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "This is onDestroy", Toast.LENGTH_SHORT).show()
    }
}
