package com.example.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.domain.model.response.Match
import com.example.team.R
import com.example.team.databinding.ItemEventsBinding

class EventRecyclerAdapter (private var events: List<Match>) :  RecyclerView.Adapter<EventRecyclerAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemEventsBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return CustomViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_events
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item: ItemEventsBinding = holder.binding
        item.event.text = events[position].event
    }

    fun setItems(events :List<Match>){
        this.events = events
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: ItemEventsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemEventsBinding = itemView
    }
}