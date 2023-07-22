package com.example.jk_0141_slots.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jk_0141_slots.R
import com.example.jk_0141_slots.data.local.models.HistoryModel

class HistoryGameAdapter : RecyclerView.Adapter<HistoryGameAdapter.HistoryHolder>() {
    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryHolder(view)
    }

    val callback = object : DiffUtil.ItemCallback<HistoryModel>() {
        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val list = AsyncListDiffer(this, callback)

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {

        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}