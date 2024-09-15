package com.example.checkingchallenges.login


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkingchallenges.R
import com.example.checkingchallenges.data.database.AppDatabase
import com.example.checkingchallenges.login.register.Register
import com.example.checkingchallenges.login.welcomeActivity.DateActivity
import com.example.checkingchallenges.repository.RepositoryUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginUser : AppCompatActivity() {


    private lateinit var repositoryUser: RepositoryUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_user)

        val usersDao = AppDatabase.getDatabase(this).userDao()
        repositoryUser = RepositoryUser(usersDao)

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
                    Toast.makeText(this, "Invalid Format", Toast.LENGTH_SHORT).show()
                }
            } else {
                emailClient.error = "Email can not be empty"
                password.error = "Password can not be empty"
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
            val user = repositoryUser.getUsers(email, password)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    val intent = Intent(this@LoginUser, DateActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginUser, "Email and Password are Incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 3
    }
}
