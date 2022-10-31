package com.rajendra.demobilelab4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.rajendra.demobilelab4.databinding.ActivityShoppingCategoryBinding

class ShoppingCategory : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ss: String = intent.getStringExtra("username").toString()
        binding.welcomeText.text = "Welcome: $ss"

        binding.beautyLayout.setOnClickListener {
            showToast("Beauty")
        }
        binding.clothingLayout.setOnClickListener {
            showToast("Clothing")
        }
        binding.electronicLayout.setOnClickListener {
            showToast("Electronic")
        }
        binding.foodLayout.setOnClickListener {
            showToast("Food")
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this,
            "You have chosen the $msg category of shopping",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}