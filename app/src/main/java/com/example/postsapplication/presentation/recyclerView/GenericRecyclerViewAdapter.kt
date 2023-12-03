package com.example.postsapplication.presentation.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GenericRecyclerViewAdapter<T, VB : ViewBinding>(
    private var items: List<T>,
    private val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB,
    private val onBind: (item: T, binding: VB) -> Unit
) : RecyclerView.Adapter<GenericRecyclerViewAdapter<T, VB>.ViewHolder>() {

    inner class ViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            onBind(item, binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<T>) {
        val oldSize = items.size
        items = newItems
        val newSize = newItems.size

        when {
            oldSize == 0 -> notifyItemRangeInserted(0, newSize)
            newSize >= oldSize -> {
                notifyItemRangeChanged(0, oldSize)
                if (newSize > oldSize) {
                    notifyItemRangeInserted(oldSize, newSize - oldSize)
                }
            }
            else -> {
                notifyItemRangeChanged(0, newSize)
                notifyItemRangeRemoved(newSize, oldSize - newSize)
            }
        }
    }

}

