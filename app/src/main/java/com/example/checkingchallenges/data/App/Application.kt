package com.example.checkingchallenges.data.App

import android.content.Context
import androidx.room.Room
import com.example.checkingchallenges.data.database.AppDatabase

object DatabaseProvider {
    private var database: AppDatabase?= null

    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this){
            val instance= Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "my_data_base"
            ).build()
            database = instance
            instance
        }
    }
}