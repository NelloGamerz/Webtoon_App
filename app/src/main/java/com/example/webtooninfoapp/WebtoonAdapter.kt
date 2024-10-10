package com.example.webtooninfoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WebtoonAdapter(private val webtoonList: List<WebtoonEntity>, private val onItemClick: (WebtoonEntity) -> Unit) : RecyclerView.Adapter<WebtoonAdapter.WebtoonViewHolder>() {

    inner class WebtoonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val webtoonImage: ImageView = itemView.findViewById(R.id.webtoonImage)
        val webtoonTitle: TextView = itemView.findViewById(R.id.webtoonTitle)
        val webtoonDescription: TextView = itemView.findViewById(R.id.webtoonDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebtoonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_webtoon, parent, false)
        return WebtoonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WebtoonViewHolder, position: Int) {
        val webtoon = webtoonList[position]
        holder.webtoonTitle.text = webtoon.title
        holder.webtoonDescription.text = webtoon.description
        // Assuming imageUrl is a URL, use Glide to load it
        Glide.with(holder.itemView.context)
            .load(webtoon.imageUrl)
            .placeholder(R.drawable.ic_launcher_background) // Add a placeholder if needed
            .into(holder.webtoonImage)

        holder.itemView.setOnClickListener{
            onItemClick(webtoon)
        }
    }

    override fun getItemCount(): Int {
        return webtoonList.size
    }
}
