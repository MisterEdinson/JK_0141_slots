package com.example.jk_0141_slots.domain

import com.example.jk_0141_slots.data.local.HistoryDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val daoHistory: HistoryDao
){

}