package com.example.jk_0141_slots.ui.home.animat

import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import com.example.jk_0141_slots.domain.utils.Constants
import com.example.jk_0141_slots.ui.home.HomeFragment
import com.example.jk_0141_slots.ui.home.HomeViewModel

class AnimationImg(
    private val viewModel: HomeViewModel,
    private val fragment: HomeFragment
){
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
        translateAnimOut.repeatCount = 10
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
                Log.e("end", "----")
            }

            override fun onAnimationRepeat(p0: Animation?) {
//                viewModel.shiftDown()
//                fragment.updateSpin()
            }
        })

        animat.addAnimation(animation)
        return animat
    }
}