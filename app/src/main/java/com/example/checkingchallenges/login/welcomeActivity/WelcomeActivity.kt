package com.example.checkingchallenges.login.welcomeActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.checkingchallenges.login.MainActivity
import com.example.checkingchallenges.R
import com.example.checkingchallenges.data.database.DateDataBase
import com.example.checkingchallenges.data.database.entities.Date
import com.example.checkingchallenges.repository.RepositoryDate
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {

    private lateinit var repositoryDate: RepositoryDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        // Initialize the DateDao and RepositoryDate
        val dateDao = DateDataBase.getDatabase(applicationContext).DateDao()
        repositoryDate = RepositoryDate(dateDao)

        val button = findViewById<Button>(R.id.buttonStart)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val pushUpText = findViewById<TextView>(R.id.pushUpText)
        val foodText = findViewById<TextView>(R.id.foodText)
        val noJerkText = findViewById<TextView>(R.id.noJerkText)

        loadStoredValues(pushUpText, foodText, noJerkText)
    }

    private fun loadStoredValues(pushUpText: TextView, foodText: TextView, noJerkText: TextView) {
        lifecycleScope.launch {
            val pushUpDate = "2024-09-14"
            val pushUpDescription = "PushUp"

            val pushUpEvent = repositoryDate.getEventByDate(pushUpDate, pushUpDescription)
            val foodEvent = repositoryDate.getEventByDate(pushUpDate, "Food")
            val noJerkEvent = repositoryDate.getEventByDate(pushUpDate, "NoJerk")

            pushUpText.text = pushUpEvent?.description ?: "No Data"
            foodText.text = foodEvent?.description ?: "No Data"
            noJerkText.text = noJerkEvent?.description ?: "No Data"
        }
    }
}
