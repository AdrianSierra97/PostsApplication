package com.example.postsapplication.presentation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericRecyclerViewAdapter<T>(
    private var items: List<T>,
    private val layoutId: Int,
    private val bind: (item: T, viewHolder: RecyclerView.ViewHolder) -> Unit
) : RecyclerView.Adapter<GenericRecyclerViewAdapter<T>.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: T) {
            bind(item, this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }
}