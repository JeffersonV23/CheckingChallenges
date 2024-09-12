package com.example.checkingchallenges.data.database.entities.dao

import User
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsersDao {

    @Insert
    suspend fun insertUsers(user: User)

    @Query("SELECT * FROM `user`")
    suspend fun getUsers(): List<User>

}