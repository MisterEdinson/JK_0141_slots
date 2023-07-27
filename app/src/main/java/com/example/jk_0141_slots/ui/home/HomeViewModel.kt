package com.example.jk_0141_slots.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jk_0141_slots.data.local.models.HistoryModel
import com.example.jk_0141_slots.domain.Repository
import com.example.jk_0141_slots.domain.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val historyData: MutableLiveData<List<HistoryModel>> = MutableLiveData()

    var money: Int = 0
    var bet: Int = 20
    val betStep: Int = 5
    var bonus: Int = 1000

    var row1 = listOf<Int>()
    var row2 = listOf<Int>()
    var row3 = listOf<Int>()
    var buf = listOf<Int>()

    //    Logic
    init {
        setup()
    }

    fun setup() {
        row1 = arrayListOf(
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random()
        )
        row2 = arrayListOf(
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random()
        )
        row3 = arrayListOf(
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random(),
            Constants.ICONS.random()
        )
    }

    fun shiftDown(){
        buf = row1
        row1 = arrayListOf(
            Constants.ICONS.random(),
            buf[0],
            buf[1],
            buf[2]
        )
        buf = row2
        row2 = arrayListOf(
            Constants.ICONS.random(),
            buf[0],
            buf[1],
            buf[2]
        )
        buf = row3
        row3 = arrayListOf(
            Constants.ICONS.random(),
            buf[0],
            buf[1],
            buf[2]
        )
    }
    //    DB
    fun getHistory() {
        viewModelScope.launch {
            val response = repo.getHistory()
            historyData.value = response
        }
    }

    fun insertHistory(insert: HistoryModel) {
        viewModelScope.launch {
            repo.addHistory(insert)
        }
    }
}