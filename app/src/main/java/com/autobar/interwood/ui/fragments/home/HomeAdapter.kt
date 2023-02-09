package com.autobar.interwood.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.autobar.interwood.data.models.Home
import com.autobar.interwood.databinding.ItemHomeBinding
import com.autobar.interwood.ui.callbacks.OnItemClickListener

class HomeAdapter(var arrayList: ArrayList<Home>, var onItemClickListener: OnItemClickListener) :
    Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemHomeBinding = ItemHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(itemHomeBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home: Home = arrayList.get(position)
        holder.binding.icon.setImageResource(home.thumbNail)
        holder.binding.title.text = home.title

        holder.binding.cardview.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class HomeViewHolder(itemHomeBinding: ItemHomeBinding) : ViewHolder(itemHomeBinding.root) {

        val binding = itemHomeBinding
    }
}