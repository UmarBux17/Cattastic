package com.cat.cattasticapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BreedsAdapter(
    private val breeds: List<responseDataClassItem>,
    private val onItemClick: (responseDataClassItem) -> Unit
) : RecyclerView.Adapter<BreedsAdapter.BreedViewHolder>() {

    // Inflate the layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false)
        return BreedViewHolder(view)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breeds[position]
        holder.bind(breed, onItemClick)
    }

    // Return the total item count
    override fun getItemCount() = breeds.size

    // ViewHolder class to represent the breed item
    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedImageView: ImageView = itemView.findViewById(R.id.breedImageView)
        private val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)

        fun bind(breed: responseDataClassItem, onItemClick: (responseDataClassItem) -> Unit) {
            breedNameTextView.text = breed.name

            // Load the breed image using Glide, if available
            Glide.with(itemView.context)
                .load("https://cdn2.thecatapi.com/images/${breed.reference_image_id}.jpg")
                .into(breedImageView)

            // Set click listener for the item
            itemView.setOnClickListener { onItemClick(breed) }
        }
    }
}
