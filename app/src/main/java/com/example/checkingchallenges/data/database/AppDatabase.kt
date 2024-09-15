package com.example.checkingchallenges.data.database


import android.content.Context
import com.example.checkingchallenges.data.database.entities.User
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.checkingchallenges.data.database.entities.dao.UserDao



@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
 abstract fun userDao(): UserDao

 companion object {
  @Volatile
  private var INSTANCE: AppDatabase? = null

  fun getDatabase(context: Context): AppDatabase {
   return INSTANCE ?: synchronized(this) {
    val instance = Room.databaseBuilder(
     context.applicationContext,
     AppDatabase::class.java,
     "app_database"
    ).build()
    INSTANCE = instance
    instance

   }
  }
 }
}
