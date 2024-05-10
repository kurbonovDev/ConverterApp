package com.example.converter.utils

import android.text.Editable
import android.text.TextWatcher
import com.example.converter.databinding.FragmentConverterBinding
import java.lang.StringBuilder

var isChangedInput = true
var isChangedOutput = true

class TextWatcherInput(
    private val binding: FragmentConverterBinding,
    private val sellValue: Float = 0f
) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {

        val str = StringBuilder(s.toString())
        if (str.length > 1) {
            if (str[0] == '0' && str[1] != '.') {
                str.insert(1, '.')
                binding.firstInputEditText.setText(str)
                binding.firstInputEditText.setSelection(str.length)
            }
        }

        isChangedOutput = false
        if (isChangedInput) {
            if (!s.isNullOrEmpty()) {
                try {
                    var sum = s.toString().toFloat()
                    sum *= sellValue
                    binding.secondInputEditText.setText(String.format("%.2f", sum))

                    binding.secondInputEditText.removeTextChangedListener(
                        TextWatcherOutput(
                            binding
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                binding.secondInputEditText.setText("0")
            }

        }

        isChangedInput = true
    }
}


class TextWatcherOutput(
    private val binding: FragmentConverterBinding,
    private var sellValue: Float = 0f
) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


    }

    override fun afterTextChanged(s: Editable?) {

        val str = StringBuilder(s.toString())
        if (str.length > 1) {
            if (str[0] == '0' && str[1] != '.') {
                str.insert(1, '.')
                binding.secondInputEditText.setText(str)
                binding.secondInputEditText.setSelection(str.length)
            }
        }
        isChangedInput = false;
        if (isChangedOutput) {

            if (!s.isNullOrEmpty()) {
                try {
                    var sum = s.toString().toFloat()
                    sum /= sellValue

                    binding.firstInputEditText.setText(String.format("%.2f", sum))
                    binding.firstInputEditText.removeTextChangedListener(
                        TextWatcherInput(
                            binding,

                            )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                binding.firstInputEditText.setText("0")
            }

        }
        isChangedOutput = true;
    }

}