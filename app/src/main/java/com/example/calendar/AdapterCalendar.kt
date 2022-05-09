package com.example.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.databinding.ItemTaskDateBinding

class AdapterCalendar(
    private val listener: OnCalendarClickListener
): RecyclerView.Adapter<BaseViewHolder<Data>>() {

    private var dateList = arrayListOf<Data>()

    fun setData(list: List<Data>) {

        dateList.clear()
        dateList.addAll(list)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dateList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Data> {
        val binding = ItemTaskDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = DataViewHolder(binding, parent.context)

        binding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener

            listener.onDateClick(dateList[position], position)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Data>, position: Int) {
        holder.bind(dateList[position], position)
    }

    inner class DataViewHolder(
        private val binding: ItemTaskDateBinding,
        private val context: Context
    ) : BaseViewHolder<Data>(binding.root) {
        override fun bind(item: Data, position: Int) {
            binding.tvDateNumber.text = item.name
        }
    }
}