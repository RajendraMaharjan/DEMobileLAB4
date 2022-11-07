package com.rajendra.demobilelab4.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rajendra.demobilelab4.databinding.ActivityProductPageBinding
import com.rajendra.demobilelab4.model.Product

class ProductPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product = Gson().fromJson(
            intent.getStringExtra("products"),
            Product::class.java
        )

        Glide.with(this).load(product.image).into(binding.imageViewProduct)
        binding.textViewTitle.text = product.title
        binding.textViewColor.text = "Color: " + product.color
        binding.textViewItemId.text = "Item Id: " + product.itemid
        binding.textViewDescription.text = product.desc

    }
}