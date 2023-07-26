package com.example.jk_0141_slots.ui.bonus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.data.local.models.HistoryModel
import com.example.jk_0141_slots.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bonus.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

@AndroidEntryPoint
class BonusFragment : Fragment() {

    val viewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bonus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rnds = (0..1000).random()
        tvBonus.text = rnds.toString()
        viewModel.bonus = rnds
        viewModel.money = viewModel.money+rnds
        viewModel.insertHistory(writeHistory(rnds))
        imgFrBonusBtnOk.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.bonusFragment, true)
                .build()
            findNavController().navigate(R.id.action_bonusFragment_to_homeFragment, null, navOptions)
        }
    }

    private fun writeHistory(bonus: Int): HistoryModel {
        val history = HistoryModel(
            id = 0,
            bet = viewModel.bet,
            win = 0,
            bonus = bonus,
            money = viewModel.money,
            date = Date().toString()
        )
        return history
    }
}