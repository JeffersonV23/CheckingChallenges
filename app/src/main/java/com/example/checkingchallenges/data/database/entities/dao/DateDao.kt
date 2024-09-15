package com.example.checkingchallenges.data.database.entities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.checkingchallenges.data.database.entities.Date


@Dao
interface DateDao {
    @Query("SELECT * FROM date WHERE date = :date AND description = :description")
suspend fun getEventByDate(date: String, description: String): Date?

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertEvent(date: Date)

}
