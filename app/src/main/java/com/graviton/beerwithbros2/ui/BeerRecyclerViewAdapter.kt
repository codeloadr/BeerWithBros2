package com.graviton.beerwithbros2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.graviton.beerwithbros2.databinding.FragmentBeerItemBinding
import com.graviton.beerwithbros2.model.Beer
import com.graviton.beerwithbros2.ui.BeerRecyclerViewAdapter.ViewHolder

/**
 * [RecyclerView.Adapter] that can display a [Beer].
 * TODO: Replace the implementation with code for your data type.
 */
class BeerRecyclerViewAdapter : ListAdapter<Beer, ViewHolder>(MyDiffUtil) {
    companion object MyDiffUtil: DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class ViewHolder(private val binding: FragmentBeerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        private val desc: TextView = binding.desc
        fun bind(beer: Beer) {
//            Glide.with(binding.image)
//                .load(beer.imageUrl)
//                .fitCenter()
//                .into(binding.image)
            name.text = beer.name
            desc.text = beer.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentBeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = getItem(position)
        holder.bind(beer)
    }

}