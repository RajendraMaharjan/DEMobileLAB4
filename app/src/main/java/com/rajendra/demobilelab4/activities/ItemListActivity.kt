package com.rajendra.demobilelab4.activities;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rajendra.demobilelab4.adapter.ItemListAdapter
import com.rajendra.demobilelab4.databinding.ActivityItemListBinding
import com.rajendra.demobilelab4.model.Product
import java.lang.reflect.Type

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type: Type = object : TypeToken<List<Product?>?>() {}.type

        val products: ArrayList<Product> = Gson().fromJson(
            intent.getStringExtra("products"), type
        )

        val adapter = ItemListAdapter(products)
        binding.recyclerViewMain.hasFixedSize()
        binding.recyclerViewMain.adapter = adapter
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
    }
}


