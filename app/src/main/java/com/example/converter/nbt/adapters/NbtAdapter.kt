package com.example.converter.nbt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.converter.databinding.NbtItemBinding
import com.example.converter.retrofit.data.models.NbtItem

class NbtAdapter(private val list: List<NbtItem>) :
    RecyclerView.Adapter<NbtAdapter.NbtViewHolder>() {
    class NbtViewHolder(private var binding: NbtItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NbtItem) {
            with(binding) {
                Glide.with(imageViewFlag)
                    .load(item.flag)
                    .circleCrop()
                    .into(imageViewFlag)
                textViewValue.text = String.format("%.6f", item.value / item.nominal) + " TJS"
                textViewCurrencyName.text = item.name
                textViewCurrencyFullName.text = item.fullName

                itemView.setOnClickListener {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbtViewHolder {
        val binding = NbtItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NbtViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NbtViewHolder, position: Int) {
        holder.bind(list[position])
    }
}