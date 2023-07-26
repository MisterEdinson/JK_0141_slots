package com.example.jk_0141_slots.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.data.local.models.HistoryModel
import com.example.jk_0141_slots.databinding.FragmentHomeBinding
import com.example.jk_0141_slots.domain.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random.Default.nextInt


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    var isButtonClickable = true
    val coroutineScope = CoroutineScope(Dispatchers.Main)

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


        imgFrHistoryBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_playFragment)
        }

        imgFrHomeBtnMoney.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_bonusFragment)
        }

        imgFrHomeBtnPlus.setOnClickListener {
            val bet = viewModel.bet
            viewModel.bet = bet + viewModel.betStep
            tvInputSpin.text = viewModel.bet.toString()
        }

        imgFrHomeBtnMinus.setOnClickListener {
            val bet = viewModel.bet
            if (bet < viewModel.betStep) {
                viewModel.bet = 0
            } else {
                viewModel.bet = bet - viewModel.betStep
            }
            tvInputSpin.text = viewModel.bet.toString()
        }
        updateSpin()
        imgFrHomeBtnSpin.setOnClickListener {
            if (viewModel.money <= 0) {
                findNavController().navigate(R.id.action_homeFragment_to_gameOverFragment)
            } else {
                if (isButtonClickable) {
                    if(Constants.BONUS_ID == nextInt(0,3)){
                        findNavController().navigate(R.id.action_homeFragment_to_bonusFragment)
                    }else{
                        isButtonClickable = false
                        animateSpin()
                        coroutineScope.launch {
                            delay(Constants.SPEED_ANIM_MAX*Constants.COUNT_REPEAT + 500)
                            isButtonClickable = true
                        }
                    }

                }
            }
        }
    }

    private fun animateSpin() {
        imgSpin1R0.startAnimation(moveDownIn())
        imgSpin1R3.startAnimation(moveDownOut())
        imgSpin1R2.startAnimation(moveDownOut())
        imgSpin1R1.startAnimation(moveDownOut())

        imgSpin2R0.startAnimation(moveDownIn())
        imgSpin2R3.startAnimation(moveDownOut())
        imgSpin2R2.startAnimation(moveDownOut())
        imgSpin2R1.startAnimation(moveDownOut())

        imgSpin3R0.startAnimation(moveDownIn())
        imgSpin3R3.startAnimation(moveDownOut())
        imgSpin3R2.startAnimation(moveDownOut())
        imgSpin3R1.startAnimation(moveDownOut())
    }


    fun updateSpin() {
        imgSpin1R0.setImageResource(viewModel.row1[0])
        imgSpin1R1.setImageResource(viewModel.row1[1])
        imgSpin1R2.setImageResource(viewModel.row1[2])
        imgSpin1R3.setImageResource(viewModel.row1[3])

        imgSpin2R1.setImageResource(viewModel.row2[0])
        imgSpin2R2.setImageResource(viewModel.row2[1])
        imgSpin2R3.setImageResource(viewModel.row2[2])

        imgSpin3R1.setImageResource(viewModel.row3[0])
        imgSpin3R2.setImageResource(viewModel.row3[1])
        imgSpin3R3.setImageResource(viewModel.row3[2])
    }

    fun moveDownOut(): Animation {
        val translateAnimOut = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        translateAnimOut.duration = Constants.SPEED_ANIM_MAX
        translateAnimOut.repeatCount = Constants.COUNT_REPEAT
        translateAnimOut.fillAfter = true

        val animat = AnimationSet(true)
        animat.addAnimation(translateAnimOut)
        return animat
    }

    fun moveDownIn(): Animation {
        val translateAnimOut = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        translateAnimOut.duration = Constants.SPEED_ANIM_MAX
        translateAnimOut.repeatCount = Constants.COUNT_REPEAT
        translateAnimOut.fillAfter = true

        val animat = AnimationSet(true)
        animat.addAnimation(translateAnimOut)

        val animation = AlphaAnimation(0f, 1f)
        animation.duration = Constants.SPEED_ANIM_MAX
        animation.repeatCount = Constants.COUNT_REPEAT
        animation.fillAfter = true

        translateAnimOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                Log.e("start", "----")
            }

            override fun onAnimationEnd(p0: Animation?) {
                resultAccess()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                viewModel.shiftDown()
                updateSpin()
            }
        })

        animat.addAnimation(animation)
        return animat
    }


    private fun resultAccess() {
        if (viewModel.row1[1] == viewModel.row2[1] && viewModel.row2[1] == viewModel.row3[1]) {
            var result: Int = 0
            result = if (viewModel.bet > viewModel.money) {
                viewModel.money + (viewModel.money * 20)
            } else {
                viewModel.money + (viewModel.bet * 20)
            }
            viewModel.money = result
            Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
            viewModel.insertHistory(writeHistory(result))
        } else if (viewModel.row1[1] == viewModel.row2[1] || viewModel.row2[1] == viewModel.row3[1] || viewModel.row1[1] == viewModel.row3[1]) {

            var result: Int = 0

            result = if (viewModel.bet > viewModel.money) {
                viewModel.money + (viewModel.money / 2)
            } else {
                viewModel.money + (viewModel.bet / 2)
            }

            viewModel.money = result
            viewModel.insertHistory(writeHistory(result))
            Toast.makeText(context, "Not bad!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        } else {
            val result = (viewModel.money - viewModel.bet)
            if (result <= 0) {
                viewModel.money = 0
                tvMoney.text = viewModel.money.toString()
                viewModel.insertHistory(writeHistory(result))
            } else {
                viewModel.money = result
                Toast.makeText(context, "You Lost!", Toast.LENGTH_SHORT).show()
                tvMoney.text = viewModel.money.toString()
                viewModel.insertHistory(writeHistory(result))
            }

        }
    }

    private fun writeHistory(win: Int): HistoryModel {
        val history = HistoryModel(
            id = 0,
            bet = viewModel.bet,
            win = win,
            bonus = 0,
            money = viewModel.money,
            date = Date().toString()
        )
        return history
    }

    private fun writeBonus(bonus: Int): HistoryModel {
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