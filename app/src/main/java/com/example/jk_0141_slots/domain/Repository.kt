package com.example.jk_0141_slots.domain

import com.example.jk_0141_slots.data.local.HistoryDao
import com.example.jk_0141_slots.data.local.models.HistoryModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val daoHistory: HistoryDao
){
    suspend fun getHistory(): List<HistoryModel>{
        return daoHistory.getHistory()
    }
    suspend fun addHistory(data: HistoryModel){
        daoHistory.insertHistory(data)
    }
}