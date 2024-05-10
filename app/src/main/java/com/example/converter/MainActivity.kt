package com.example.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.exchanger.ExchangerFragment
import com.example.converter.nbt.NbtFragment
import com.example.converter.utils.APP_ACTIVITY
import com.example.converter.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        APP_ACTIVITY = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(NbtFragment(), false)
        setBottomNavigationListener()
    }


    private fun setBottomNavigationListener() {

        binding.bottomNbar.selectedItemId = R.id.menu_nbt
        binding.bottomNbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_obmen -> {
                    replaceFragment(ExchangerFragment(), false)
                }
                R.id.menu_nbt -> {
                    replaceFragment(NbtFragment(), false)
                }
            }
            true
        }
    }
}