package com.example.checkingchallenges.data.app

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
            ).fallbackToDestructiveMigration()
                .build()
            database = instance
            instance


        }
    }
}