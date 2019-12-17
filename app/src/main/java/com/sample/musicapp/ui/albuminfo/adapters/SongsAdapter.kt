package com.sample.musicapp.ui.albuminfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.musicapp.R
import com.sample.musicapp.domain.entities.SongInfo

class SongsAdapter(
    private var items: List<SongInfo> = emptyList()
) : RecyclerView.Adapter<SongsAdapter.ItemHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.song_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
    }

    fun onNewItems(results: List<SongInfo>) {
        items = results
        notifyDataSetChanged()
    }


    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.song_name)

        fun bind(item: SongInfo) {
            name.text = String.format(
                itemView.context.getString(R.string.title_song),
                item.trackNumber.toString(),
                item.name
            )
        }
    }
}
