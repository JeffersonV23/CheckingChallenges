package com.example.checkingchallenges.repository


import com.example.checkingchallenges.data.database.entities.User
import com.example.checkingchallenges.data.database.entities.dao.UserDao



class RepositoryUser(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUsers(email: String, password: String): User? {
        return userDao.getUser(email, password)


    }
}

