package com.example.jk_0141_slots.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history_game")
data class HistoryModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var money: Int? = null,
    var win:Int? = null,
    var bet: Int? = null,
    var bonus: Int? = null,
    var date: String? = null
    )