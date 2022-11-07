package com.rajendra.demobilelab4.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rajendra.demobilelab4.activities.ProductPageActivity
import com.rajendra.demobilelab4.model.Product
import com.rajendra.demobilelab4.databinding.RecyclerListItemBinding

class ItemListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(private val binding: RecyclerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.textViewTitle.text = product.title
            binding.textViewColor.text = "Color : " + product.color
            binding.textViewPrice.text = "Price : $" + product.price

            Glide.with(binding.root.context).load(product.image).into(binding.imageViewItem)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ProductPageActivity::class.java)
                intent.putExtra("products", Gson().toJson(product))
                binding.root.context.startActivity(intent)
            }
        }
    }
}