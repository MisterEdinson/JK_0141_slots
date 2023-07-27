package com.example.jk_0141_slots.ui.home

import android.os.Bundle
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
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.databinding.FragmentHomeBinding
import com.example.jk_0141_slots.domain.utils.Constants
import com.example.jk_0141_slots.ui.home.logic_spin.ActionsSpinImg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random.Default.nextInt


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    var isButtonClickable = true
    var isSpin = true
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

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
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.bonusFragment, true)
                .build()
            findNavController().navigate(R.id.action_homeFragment_to_playFragment,null,navOptions)
        }

        imgFrHomeBtnMoney.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.bonusFragment, true)
                .build()
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment, null, navOptions)
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
            if (isButtonClickable) {
                if (Constants.BONUS_ID == nextInt(0, 10) && viewModel.money > 0) {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.bonusFragment, true)
                        .build()
                    findNavController().navigate(R.id.action_homeFragment_to_bonusFragment, null, navOptions)
                } else {
                    isButtonClickable = false
                    animateSpin()
                    coroutineScope.launch {
                        delay(Constants.SPEED_ANIM_MAX * Constants.COUNT_REPEAT + 500)
                        isButtonClickable = true
                        isSpin = true
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

        imgSpin2R0.setImageResource(viewModel.row2[0])
        imgSpin2R1.setImageResource(viewModel.row2[1])
        imgSpin2R2.setImageResource(viewModel.row2[2])
        imgSpin2R3.setImageResource(viewModel.row2[3])

        imgSpin3R1.setImageResource(viewModel.row3[0])
        imgSpin3R1.setImageResource(viewModel.row3[1])
        imgSpin3R2.setImageResource(viewModel.row3[2])
        imgSpin3R3.setImageResource(viewModel.row3[3])
    }

    private fun moveDownOut(): Animation {
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

    private fun moveDownIn(): Animation {
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
                if (isSpin) {
                    resultAccess()
                    isSpin = false
                }
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
        if (viewModel.row1[2] == viewModel.row2[2] && viewModel.row2[2] == viewModel.row3[2]) {
            ActionsSpinImg(viewModel).win()
            Toast.makeText(context, "Win!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        } else if (viewModel.row1[2] == viewModel.row2[2] || viewModel.row2[2] == viewModel.row3[2] || viewModel.row1[2] == viewModel.row3[2]) {
            ActionsSpinImg(viewModel).halfWin()
            Toast.makeText(context, "Half Win!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        } else {
            ActionsSpinImg(viewModel).lost()
            if (viewModel.money == 0) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.bonusFragment, true)
                    .build()
                findNavController().navigate(R.id.action_homeFragment_to_gameOverFragment, null, navOptions)
            }
            Toast.makeText(context, "Lost!", Toast.LENGTH_SHORT).show()
            tvMoney.text = viewModel.money.toString()
        }
    }

}