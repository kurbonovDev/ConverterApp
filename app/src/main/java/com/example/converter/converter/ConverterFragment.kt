package com.example.converter.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.converter.R
import com.example.converter.databinding.FragmentConverterBinding
import com.example.converter.retrofit.data.models.Currency
import com.example.converter.utils.TextWatcherInput
import com.example.converter.utils.TextWatcherOutput


class ConverterFragment(private var item: Currency) : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun setData(){
        binding.firstInputEditText.addTextChangedListener(TextWatcherInput(binding,item.sellValue))
        binding.secondInputEditText.addTextChangedListener(TextWatcherOutput(binding,item.sellValue))


        binding.currencyText.text =  "1${item.name} = ${ String.format("%.2f",item.sellValue)}c"

        binding.firstInputContainer.hint="У вас есть ${item.name}"
        binding.firstInputContainer.isHintEnabled=true
        Glide.with(requireActivity())
            .load(item.flag)
            .error(R.drawable.no_data_image)
            .into(binding.currenceFlag)

        binding.textViewCurrencyName.text = item.name
        binding.textViewCurrencyFullName.text = item.fullName
        binding.textViewValue.text = "${item.sellValue*100}c "
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}