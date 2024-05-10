package com.example.converter.exchanger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converter.databinding.FragmentExchangerBinding
import com.example.converter.exchanger.adapters.ExchangerAdapter
import com.example.converter.retrofit.Retrofit
import com.example.converter.retrofit.data.models.ExchangerItem
import kotlinx.coroutines.launch


class ExchangerFragment : Fragment() {

    private var _binding: FragmentExchangerBinding? = null
    private val binding get() = _binding!!
    private var list = mutableListOf<ExchangerItem>()
    private lateinit var rcViewExchanger: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangerBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.bntError.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.loadProgressbar.isVisible = true
            binding.errorImage.isVisible = false
            binding.errorText.isVisible = false
            binding.bntError.isVisible = false
            try {
                list = Retrofit.getBanksRates()
                val iterator = list.iterator()
                while (iterator.hasNext()) {
                    val item = iterator.next()
                    var bool = false
                    item.currency.forEach { currency ->
                        if (currency.buyValue.toDouble() == 0.0000 || currency.sellValue.toDouble() == 0.0000) {
                            bool = true
                        }
                    }
                    if (bool) iterator.remove()
                }

                rcViewExchanger = binding.rcViewExchanger
                rcViewExchanger.adapter = ExchangerAdapter(list)
                rcViewExchanger.layoutManager = LinearLayoutManager(requireActivity())
                binding.loadProgressbar.isVisible = false

            } catch (e: Exception) {
                e.printStackTrace()
                binding.errorImage.isVisible = true
                binding.errorText.isVisible = true
                binding.errorText.text = e.message
                binding.bntError.isVisible = true
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}