package com.example.converter.nbt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.converter.R
import com.example.converter.databinding.FragmentNbtBinding
import com.example.converter.nbt.adapters.NbtAdapter
import com.example.converter.retrofit.Retrofit
import com.example.converter.retrofit.data.models.NbtItem
import kotlinx.coroutines.launch


class NbtFragment : Fragment() {


    private var _binding: FragmentNbtBinding? = null
    private val binding get() = _binding!!
    private var list = listOf<NbtItem>()
    private lateinit var rcViewNbtRates: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNbtBinding.inflate(layoutInflater, container, false)
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
            binding.errorText.isVisible = false
            binding.bntError.isVisible = false
            binding.errorImage.isVisible = false
            binding.blockRcView.isVisible = false
            try {

                list = Retrofit.getNbtRates()
                binding.blockRcView.isVisible = true
                rcViewNbtRates = binding.rcViewNbt

                rcViewNbtRates.adapter = NbtAdapter(list)
                binding.loadProgressbar.isVisible = false
            } catch (e: Exception) {
                binding.errorImage.isVisible = true
                binding.errorText.text = "Что то пошло не так: ${e.message}"
                binding.errorText.isVisible = true
                binding.blockRcView.isVisible = false
                binding.bntError.isVisible = true
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}