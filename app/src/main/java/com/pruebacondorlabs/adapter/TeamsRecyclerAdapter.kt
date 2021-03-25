package com.pruebacondorlabs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pruebacondorlabs.R
import com.pruebacondorlabs.databinding.ItemTeamBinding
import com.example.domain.model.response.Teams
import com.pruebacondorlabs.viewModel.ItemTeamViewModel

class TeamsRecyclerAdapter(private var teams: List<Teams>, private val onUsersListener: OnUsersListener) : RecyclerView.Adapter<TeamsRecyclerAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemTeamBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return CustomViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_team
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val itemViewModel = ItemTeamViewModel()
        val item: ItemTeamBinding = holder.binding
        itemViewModel.teams(teams[position])
        item.viewModel = itemViewModel
        item.item.setOnClickListener {
            onUsersListener.onItemClick(teams[position])
        }
    }

    interface OnUsersListener {
        fun onItemClick(teams: Teams)
    }

    fun setItems(teams :List<Teams>){
        this.teams = teams
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: ItemTeamBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemTeamBinding = itemView
    }


}