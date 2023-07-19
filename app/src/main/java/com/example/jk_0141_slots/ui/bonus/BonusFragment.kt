package com.example.jk_0141_slots.ui.bonus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import kotlinx.android.synthetic.main.fragment_bonus.*

class BonusFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bonus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgFrBonusBtnOk.setOnClickListener {
            findNavController().navigate(R.id.action_bonusFragment_to_homeFragment)
        }
    }
}