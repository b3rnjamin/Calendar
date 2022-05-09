package com.example.calendar

import androidx.recyclerview.widget.DiffUtil

class DiffUtilData(
    private val oldValues: List<Data>,
    private val newValues: List<Data>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldValues[oldItemPosition] == newValues[newItemPosition]

    override fun getOldListSize(): Int = oldValues.size

    override fun getNewListSize(): Int = newValues.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldValues[oldItemPosition]
        val newItem = newValues[newItemPosition]
        return (oldItem == newItem)
    }
}