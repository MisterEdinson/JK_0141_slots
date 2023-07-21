package com.example.jk_0141_slots.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var money: Int = 3000
    var bet: Int = 20
    val betStep: Int = 5
    var bonus: Int = 1000
}