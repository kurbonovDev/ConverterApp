package com.example.converter.exchanger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.converter.databinding.ExchangerItemBinding
import com.example.converter.retrofit.data.models.ExchangerItem

class ExchangerAdapter(private val list: List<ExchangerItem>) :
    RecyclerView.Adapter<ExchangerAdapter.ExchangerViewHolder>() {
    class ExchangerViewHolder(val binding: ExchangerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangerViewHolder {
        val binding =
            ExchangerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ExchangerViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(root)
                .load(list[position].icon)
                .into(exchangerImage)

            bankName.text = list[position].bankName
            exRcViewItem.adapter = ExRcViewAdapter(list[position])
            exRcViewItem.layoutManager = LinearLayoutManager(holder.itemView.context)
        }
    }
}