package com.example.checkingchallenges.repository

import com.example.checkingchallenges.data.database.entities.Date
import com.example.checkingchallenges.data.database.entities.dao.DateDao

class RepositoryDate(private val dateDao: DateDao) {

    suspend fun addDate(date: Date) {
        dateDao.insertEvent(date)
    }

    suspend fun getEventByDate(date: String, description: String): Date? {
        return dateDao.getEventByDate(date, description)
    }
}
