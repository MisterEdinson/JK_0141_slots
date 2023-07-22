package com.example.jk_0141_slots.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jk_0141_slots.data.local.models.HistoryModel

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_game ORDER BY date DESC")
    suspend fun getHistory(): List<HistoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryModel)
}