package com.starwars.starwarsviewer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.starwars.starwarsviewer.R
import com.starwars.starwarsviewer.domain.planet.model.Planet

class PlanetAdapter(diffCallback: DiffUtil.ItemCallback<Planet>, private val clickCallback: (Int) -> Unit):
    PagingDataAdapter<Planet,  PlanetAdapter.PlanetViewHolder>(diffCallback) {

    inner class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val planetName: TextView = itemView.findViewById(R.id.name_value_tv)
        private val terrainType: TextView = itemView.findViewById(R.id.terrain_type_value_tv)

        fun bind(planet: Planet?) {
            planetName.text = planet?.name
            terrainType.text = planet?.terrain
            val paths = planet?.url?.split("/")!!
            val id = paths[paths.size - 2].toInt()
            itemView.setOnClickListener { clickCallback(id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_list_item,
            parent, false)
        return PlanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = getItem(position)
        holder.bind(planet)
    }

    object PlanetComparator : DiffUtil.ItemCallback<Planet>() {
        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            // Id is unique.
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem == newItem
        }
    }
}