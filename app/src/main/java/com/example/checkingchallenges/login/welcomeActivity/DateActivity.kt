package com.example.checkingchallenges.login.welcomeActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkingchallenges.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var eventEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var eventTextView: TextView


    private val events = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        calendarView = findViewById(R.id.calendarView)
        eventEditText = findViewById(R.id.eventEditText)
        saveButton = findViewById(R.id.saveButton)
        eventTextView = findViewById(R.id.eventTextView)

        var selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year)


            val event = events[selectedDate]
            eventTextView.text = event ?: "there are no events today"
        }

        saveButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            val eventText = eventEditText.text.toString()

            if (eventText.isNotEmpty()) {

                events[selectedDate] = eventText


                eventTextView.text = eventText
                eventEditText.text.clear()
                Toast.makeText(this, "Event saved for $selectedDate", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pls add a service", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
