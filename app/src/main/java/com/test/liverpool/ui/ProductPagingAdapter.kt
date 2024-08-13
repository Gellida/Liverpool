package com.test.liverpool.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.liverpool.data.model.Record
import com.test.liverpool.databinding.ItemProductGridBinding

class ProductPagingAdapter(
    private val onClick: (Record) -> Unit
) : PagingDataAdapter<Record, ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }
    }
}

class ProductViewHolder(
    private val binding: ItemProductGridBinding,
    private val onClick: (Record) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Record) {
        binding.textName.text = product.toString()
        binding.root.setOnClickListener { onClick(product) }
        //binding.executePendingBindings()
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem == newItem
    }
}
