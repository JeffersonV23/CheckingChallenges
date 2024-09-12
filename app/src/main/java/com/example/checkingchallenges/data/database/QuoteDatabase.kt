package com.example.checkingchallenges.data.database

import User
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.checkingchallenges.data.database.entities.dao.UsersDao


@Database(entities = [User::class], version = 1)
 abstract class AppDatabase: RoomDatabase() {

  abstract fun usersDao(): UsersDao


}