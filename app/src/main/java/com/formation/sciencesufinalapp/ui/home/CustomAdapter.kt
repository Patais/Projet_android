package com.formation.sciencesufinalapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.formation.sciencesufinalapp.R

class CustomAdapter(private val mList: List<ItemsViewModel>) : Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.playername.text = "${itemsViewModel.name} :"

        // sets the text to the textview from our itemHolder class
        holder.scoreText.text = "${itemsViewModel.score} ${if (itemsViewModel.score.toInt() < 2) "point" else "points"}"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val playername: TextView = itemView.findViewById(R.id.textViewName)
        val scoreText: TextView = itemView.findViewById(R.id.textViewScore)
    }
}