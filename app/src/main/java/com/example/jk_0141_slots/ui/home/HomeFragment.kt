package com.example.jk_0141_slots.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    val viewModel : HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    private val array = intArrayOf(
        R.drawable.ico_1,
        R.drawable.ico_2,
        R.drawable.ico_3,
        R.drawable.ico_4,
        R.drawable.ico_5,
        R.drawable.ico_6,
        R.drawable.ico_7,
        R.drawable.ico_8,
    )

    private var cycle = 5
    private var row1Images = listOf<Int>()
    private var row2Images = listOf<Int>()
    private var row3Images = listOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
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

        imgFrHomeBtnSpin.setOnClickListener {
            setupSpin()
        }
    }
    private fun animateSpin(){
        val translateAnim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        translateAnim.duration = 1000 // Длительность анимации в миллисекундах
        translateAnim.fillAfter = true

        val animat = AnimationSet(true)
        animat.addAnimation(translateAnim)

        imgSpin2R3.startAnimation(animat)
        imgSpin2R2.startAnimation(animat)
        imgSpin2R1.startAnimation(animat)
    }

    private fun setupSpin(){

        row1Images = arrayListOf(array.random(), array.random() , array.random())
        row2Images = arrayListOf(array.random(), array.random() , array.random())
        row3Images = arrayListOf(array.random(), array.random() , array.random())

        imgSpin1R1.setImageResource(row1Images[0])
        imgSpin1R2.setImageResource(row1Images[1])
        imgSpin1R3.setImageResource(row1Images[2])

        imgSpin2R1.setImageResource(row2Images[0])
        imgSpin2R2.setImageResource(row2Images[1])
        imgSpin2R3.setImageResource(row2Images[2])

        imgSpin3R1.setImageResource(row3Images[0])
        imgSpin3R2.setImageResource(row3Images[1])
        imgSpin3R3.setImageResource(row3Images[2])
        resultAccess()
    }

    private fun shiftStep(){
        row1Images = arrayListOf(array.random(), row1Images[0] , row1Images[1])
        row2Images = arrayListOf(array.random(), row1Images[0] , row1Images[1])
        row3Images = arrayListOf(array.random(), row1Images[0] , row1Images[1])

        imgSpin1R1.setImageResource(row1Images[0])
        imgSpin1R2.setImageResource(row1Images[1])
        imgSpin1R3.setImageResource(row1Images[2])
    }

    private fun resultAccess(){
        if(row1Images[1] == row2Images[1] && row2Images[1] == row3Images[1]){
            val result = (viewModel.bet*20)+viewModel.money
            viewModel.money = result
            Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        }else if(row1Images[1] == row2Images[1] || row2Images[1] == row3Images[1] || row1Images[1] == row3Images[1]){
            val result = viewModel.money+viewModel.bet
            viewModel.money = result
            Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        }else{
            val result = viewModel.money-viewModel.bet
            viewModel.money = result
            Toast.makeText(context, "You Lost!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        }
    }
}