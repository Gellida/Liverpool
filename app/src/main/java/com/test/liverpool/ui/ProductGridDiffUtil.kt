package com.test.liverpool.ui

import androidx.recyclerview.widget.DiffUtil
import com.test.liverpool.data.model.Record


class ProductGridDiffUtil(
    private val oldList: List<Record>,
    private val newList: List<Record>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].productId == newList[newItemPosition].productId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }
}