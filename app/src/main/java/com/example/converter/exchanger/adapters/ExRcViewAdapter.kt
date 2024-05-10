package com.example.converter.exchanger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.converter.converter.ConverterFragment
import com.example.converter.databinding.ExRcviewItemBinding
import com.example.converter.retrofit.data.models.ExchangerItem
import com.example.converter.utils.replaceFragment

class ExRcViewAdapter(private var item: ExchangerItem) :
    RecyclerView.Adapter<ExRcViewAdapter.ExchangerRcItemViewHolder>() {


    class ExchangerRcItemViewHolder(val binding: ExRcviewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangerRcItemViewHolder {
        val binding =
            ExRcviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangerRcItemViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: ExchangerRcItemViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(root)
                .load(item.currency[position].flag)
                .into(exRcItemImage)

            exRcItemName.text = item.currency[position].name
            ecRcItemBuy.text = item.currency[position].buyValue.toString()
            ecRcItemSell.text = item.currency[position].sellValue.toString()
            blockExRc.setOnClickListener {
                replaceFragment(ConverterFragment(item.currency[position]))
            }


        }
    }
}