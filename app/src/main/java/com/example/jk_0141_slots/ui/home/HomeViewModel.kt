package com.example.jk_0141_slots.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val money: Int = 3000
    var bet: Int = 20
    val betStep: Int = 5
    val bonus: Int = 1000
}