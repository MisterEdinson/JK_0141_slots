package com.example.jk_0141_slots.ui.home.logic_spin

import com.example.jk_0141_slots.data.local.models.HistoryModel
import com.example.jk_0141_slots.ui.home.HomeViewModel
import java.util.*


class ActionsSpinImg(private val viewModel: HomeViewModel) {

    fun win(){
        val result = if (viewModel.bet > viewModel.money){
            (viewModel.money * 10)
        }else{
            (viewModel.bet * 10)
        }
        viewModel.money += result
        viewModel.insertHistory(writeHistory("+${result}"))
    }

    fun halfWin(){
        val result = if (viewModel.bet > viewModel.money) {
            (viewModel.money / 2)
        } else {
            (viewModel.bet / 2)
        }
        viewModel.money += result
        viewModel.insertHistory(writeHistory("+${result}"))
    }

    fun lost(){
        val result = (viewModel.money - viewModel.bet)
        if (result <= 0) {
            viewModel.money = 0
            viewModel.insertHistory(writeHistory("-${result}"))
        } else {
            viewModel.money = result
            viewModel.insertHistory(writeHistory("-${viewModel.bet}"))
        }
    }

    private fun writeHistory(win: String): HistoryModel {
        return HistoryModel(
            id = 0,
            bet = viewModel.bet,
            win = win,
            bonus = 0,
            money = viewModel.money,
            date = Date().toString()
        )
    }
}