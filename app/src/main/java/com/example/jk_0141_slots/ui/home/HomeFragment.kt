package com.example.jk_0141_slots.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val viewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMoney.text = viewModel.money.toString()
        tvInputSpin.text = viewModel.bet.toString()

        imgFrHomeBtnBack.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_playFragment)
        }

        imgFrHomeBtnMoney.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bonusFragment)
        }

        imgFrHomeBtnPlus.setOnClickListener {
            val bet = viewModel.bet
            viewModel.bet = bet + viewModel.betStep
            tvInputSpin.text = viewModel.bet.toString()
        }

        imgFrHomeBtnMinus.setOnClickListener {
            val bet = viewModel.bet
            if(bet < viewModel.betStep){
                viewModel.bet = 0
            }else{
                viewModel.bet = bet - viewModel.betStep
            }
            tvInputSpin.text = viewModel.bet.toString()
        }
    }
}