package com.sample.musicapp.ui.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sample.musicapp.R
import com.sample.musicapp.domain.entities.SearchResultItem

class SearchAdapter(
    private var items: List<SearchResultItem>,
    val onItemClick:(albumId: Int) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ItemHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.search_result_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
    }

    fun onNewItems(results: List<SearchResultItem>) {
        items = results
        notifyDataSetChanged()
    }


    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemLayout: CardView = view.findViewById(R.id.search_item)
        private val itemName: TextView = view.findViewById(R.id.search_item_name)

        fun bind(item: SearchResultItem) {
            itemName.text = item.albumName
            itemLayout.setOnClickListener {
                onItemClick(item.albumId)
            }
        }
    }
}
