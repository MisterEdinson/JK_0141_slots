package com.example.jk_0141_slots.ui.home

import androidx.lifecycle.ViewModel
import com.example.jk_0141_slots.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    var money: Int = 3000
    var bet: Int = 20
    val betStep: Int = 5
    var bonus: Int = 1000
}