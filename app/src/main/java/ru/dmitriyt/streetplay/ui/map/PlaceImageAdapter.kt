package ru.dmitriyt.streetplay.ui.map

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_place_image.view.*
import ru.dmitriyt.streetplay.R

class PlaceImageAdapter(private val images: List<String>): RecyclerView.Adapter<PlaceImageAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_place_image, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(image: String) {
            Glide.with(itemView).load(image).into(itemView.image)
        }
    }
}