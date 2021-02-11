package com.pruebacondorlabs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pruebacondorlabs.R
import com.pruebacondorlabs.databinding.ItemTeamBinding
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.viewModel.ItemTeamViewModel

class TeamsRecyclerAdapter(private val context: Context, private var teams: List<Teams>) : RecyclerView.Adapter<TeamsRecyclerAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamsRecyclerAdapter.CustomViewHolder {
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


    override fun onBindViewHolder(holder: TeamsRecyclerAdapter.CustomViewHolder, position: Int) {
        val itemViewModel = ItemTeamViewModel()
        val item: ItemTeamBinding = holder.binding


    }

    fun setItems(teams :List<Teams>){
        this.teams = teams
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: ItemTeamBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding: ItemTeamBinding = itemView
    }


}