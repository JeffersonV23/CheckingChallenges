package com.example.checkingchallenges.repository

import User
import android.content.Context
import com.example.checkingchallenges.data.App.DatabaseProvider
import com.example.checkingchallenges.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryUser(private val context: Context) {

    private val db: AppDatabase = DatabaseProvider.getDatabase(context)
    private val usersDao = db.usersDao()

    fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            usersDao.insertUsers(user)
        }
    }

    fun getUsers(callback: (List<User>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val users = usersDao.getUsers()
            callback(users)
        }
    }
}
