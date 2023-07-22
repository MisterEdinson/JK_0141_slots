package com.example.jk_0141_slots.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jk_0141_slots.data.local.models.HistoryModel
import com.example.jk_0141_slots.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val historyData: MutableLiveData<List<HistoryModel>> = MutableLiveData()

    var money: Int = 3000
    var bet: Int = 20
    val betStep: Int = 5
    var bonus: Int = 1000

    suspend fun getHistory(){
        viewModelScope.launch {
            val response = repo.getHistory()
            historyData.value = response
        }
    }

    suspend fun insertHistory(insert: HistoryModel){
        viewModelScope.launch {
            repo.addHistory(insert)
        }
    }
}