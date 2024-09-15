package com.example.checkingchallenges.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.checkingchallenges.data.database.entities.Date
import com.example.checkingchallenges.data.database.entities.dao.DateDao

@Database(entities = [Date::class], version = 1)
abstract class DateDataBase : RoomDatabase() {
    abstract fun DateDao(): DateDao

    companion object {
        @Volatile
        private var INSTANCE: DateDataBase? = null

        fun getDatabase(context: Context): DateDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DateDataBase::class.java,
                    "date_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
