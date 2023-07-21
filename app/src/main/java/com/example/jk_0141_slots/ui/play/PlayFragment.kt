package com.example.jk_0141_slots.ui.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_play.*

class PlayFragment : Fragment() {

    val viewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imFrPlayBtn.setOnClickListener{
            if(viewModel.money == 0){
                viewModel.money = 3000
            }
            findNavController().navigate(R.id.action_playFragment_to_homeFragment)
        }
    }
}