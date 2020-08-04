package com.rahulad.creaturemon.view.allcreatures

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulad.creaturemon.R
import com.rahulad.creaturemon.app.inflate
import com.rahulad.creaturemon.model.Creature
import kotlinx.android.synthetic.main.list_item_creature.view.*

/**
 * Created by Rahul Lad on 7/25/2020.
 */

class CreatureAdapter(private val creatures: MutableList<Creature>)
    : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature))
    }

    override fun onBindViewHolder(holder: CreatureAdapter.ViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount() = creatures.size

    fun updateCreatures(creatures: List<Creature>) {
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var creature: Creature

        fun bind(creature: Creature) {
            this.creature = creature
            itemView.avatarListItem.setImageDrawable(itemView.context.getDrawable(creature.drawables))
            itemView.name.text = creature.name
            itemView.hitPoints.text = creature.hitPoints.toString()
        }
    }
}