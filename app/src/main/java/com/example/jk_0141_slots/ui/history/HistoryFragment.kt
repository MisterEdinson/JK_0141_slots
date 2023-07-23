package com.example.jk_0141_slots.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {


    private var adapter: HistoryGameAdapter? = null
    val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getHistory()
        viewModel.historyData.observe(viewLifecycleOwner) {
            adapter?.list?.submitList(it)
        }

        imgFrHistoryBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
        }
    }

    fun initAdapter(){
        adapter = HistoryGameAdapter()
        rvHistory.adapter = adapter
    }
}