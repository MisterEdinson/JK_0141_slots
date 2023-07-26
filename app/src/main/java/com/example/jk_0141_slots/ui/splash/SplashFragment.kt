package com.example.jk_0141_slots.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun showLoadingProgress(percentage: Int) {
            tvLoader.text = "${percentage}%"
        }

        suspend fun simulateLoading() {
            val totalDurationMillis = 4500
            val updateIntervalMillis = 100
            var progress = 0
            val startTime = System.currentTimeMillis()

            while (progress <= 100) {
                when(progress){
                    8 -> {
                        imgLoaderItem0.visibility = View.VISIBLE
                    }
                    16 -> {
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                    }
                    24->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                    }
                    32->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                    }
                    40->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                    }
                    48->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                    }
                    54->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                    }
                    62->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                        imgLoaderItem7.visibility = View.VISIBLE
                    }
                    70->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                        imgLoaderItem7.visibility = View.VISIBLE
                        imgLoaderItem8.visibility = View.VISIBLE
                    }
                    78->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                        imgLoaderItem7.visibility = View.VISIBLE
                        imgLoaderItem8.visibility = View.VISIBLE
                        imgLoaderItem9.visibility = View.VISIBLE
                    }
                    86->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                        imgLoaderItem7.visibility = View.VISIBLE
                        imgLoaderItem8.visibility = View.VISIBLE
                        imgLoaderItem9.visibility = View.VISIBLE
                        imgLoaderItem10.visibility = View.VISIBLE
                    }
                    94->{
                        imgLoaderItem0.visibility = View.VISIBLE
                        imgLoaderItem1.visibility = View.VISIBLE
                        imgLoaderItem2.visibility = View.VISIBLE
                        imgLoaderItem3.visibility = View.VISIBLE
                        imgLoaderItem4.visibility = View.VISIBLE
                        imgLoaderItem5.visibility = View.VISIBLE
                        imgLoaderItem6.visibility = View.VISIBLE
                        imgLoaderItem7.visibility = View.VISIBLE
                        imgLoaderItem8.visibility = View.VISIBLE
                        imgLoaderItem9.visibility = View.VISIBLE
                        imgLoaderItem10.visibility = View.VISIBLE
                        imgLoaderItem11.visibility = View.VISIBLE
                    }
                }
                showLoadingProgress(progress)
                delay(updateIntervalMillis.toLong())
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                progress = ((elapsedTime * 100) / totalDurationMillis).toInt()
            }
        }

        coroutineScope.launch {
            simulateLoading()
            val navController = findNavController()
            navController.navigate(R.id.action_splashFragment_to_playFragment)
        }
    }
}