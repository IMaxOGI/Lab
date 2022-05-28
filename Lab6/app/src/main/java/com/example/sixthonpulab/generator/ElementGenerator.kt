package com.example.sixthonpulab.generator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthonpulab.MainActivity
import com.example.sixthonpulab.R
import com.example.sixthonpulab.databinding.ItemComponentBinding

class ElementGenerator (private val callback: (MainActivity.MyComponent) -> Unit)
    : ListAdapter<MainActivity.MyComponent, MyViewHolder>(MyCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_component,
                parent,
                false
            )
        return MyViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MyViewHolder(itemView: View, private val callback: (MainActivity.MyComponent) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ItemComponentBinding.bind(itemView)


    fun bind(itemModel: MainActivity.MyComponent) {
        binding.apply {
            elementText.text = itemModel.randNumber.toString()
            elementText.setBackgroundColor(itemModel.color)
            elementText.setOnClickListener {
                callback(itemModel)
            }
        }
    }
}

class MyCallback : DiffUtil.ItemCallback<MainActivity.MyComponent>() {
    override fun areItemsTheSame(oldItem: MainActivity.MyComponent, newItem: MainActivity.MyComponent): Boolean {
        return oldItem.randNumber == newItem.randNumber
    }

    override fun areContentsTheSame(oldItem: MainActivity.MyComponent, newItem: MainActivity.MyComponent): Boolean {
        return oldItem.randNumber == newItem.randNumber
    }

}