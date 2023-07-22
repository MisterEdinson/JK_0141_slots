package com.example.jk_0141_slots.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jk_0141_slots.data.local.models.HistoryModel

@Database(entities = [HistoryModel::class], version = 1, exportSchema = false)
abstract class GameDataBase : RoomDatabase(){
    abstract fun historyGame() : HistoryDao
}