package com.app.demo.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.demo.R
import com.app.demo.databinding.ListItemBinding


class DemoAdapter(private val itemClick: ItemClick) :
    ListAdapter<String, DemoAdapter.ItemViewholder>(DemoCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val binding= DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
                R.layout.list_item, parent, false)
        return ItemViewholder(binding)
    }

    override fun onBindViewHolder(holder: DemoAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.titleText.text=item
            binding.titleText.setOnClickListener {
                itemClick.onItemClick(item)
            }

            binding.executePendingBindings()
        }
    }
}

interface ItemClick{
    fun onItemClick(item:String)
}

class DemoCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
