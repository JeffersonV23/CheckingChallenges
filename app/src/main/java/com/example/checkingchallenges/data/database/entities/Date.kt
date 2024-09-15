package com.example.checkingchallenges.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class Date(
@PrimaryKey val date: String,
    val description : String )