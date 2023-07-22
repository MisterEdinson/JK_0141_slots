package com.example.jk_0141_slots.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.jk_0141_slots.data.local.models.HistoryModel

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_game")
    suspend fun getHistory(): List<HistoryModel>
}