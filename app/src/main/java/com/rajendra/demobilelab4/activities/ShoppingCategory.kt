package com.rajendra.demobilelab4.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.gson.Gson
import com.rajendra.demobilelab4.databinding.ActivityShoppingCategoryBinding
import com.rajendra.demobilelab4.model.Product

class ShoppingCategory : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    private val foodProductList = ArrayList<Product>()

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
            foodProductList.addAll(
                arrayOf(
                    Product(
                        "Rainbow  Smoothies",
                        8.99,
                        "Rainbow",
                        "https://cdn.loveandlemons.com/wp-content/uploads/2015/06/IMG_2015_06_10_05144.jpg",
                        "food1",
                        "Let’s eat the rainbow! Or, with these healthy breakfast smoothies, drink the rainbow through a straw (or scoop it up with a spoon… how you like to enjoy your smoothie is really up to you)."
                    ),
                    Product(
                        "Nori Wraps",
                        11.99,
                        "green",
                        "https://cdn.loveandlemons.com/wp-content/uploads/2018/06/IMG_12352-3-cropped-final.jpg",
                        "food2",
                        "If you love sushi but don't love rolling it at home, these easy nori wraps are for you. They taste just as good, especially with spicy mayo drizzled on top!."
                    ), Product(
                        "Steamed Bao Buns",
                        7.35,
                        "yellow",
                        "https://cdn.loveandlemons.com/wp-content/uploads/2020/02/IMG_23887-cropped-3.jpg",
                        "food3",
                        "These steamed bao buns are one of our favorite recipes to make on a date night in. I prep the filling while Jack mixes up the dough. Then, we eat!"
                    ),
                    Product(
                        "Avocado Toast",
                        15.44,
                        "green",
                        "https://cdn.loveandlemons.com/wp-content/uploads/2020/01/how-to-make-avocado-toast.jpg",
                        "food4",
                        "Love avocado toast? Learn how to take it to the next level with this simple recipe (+5 amazing variations!) and my best tips and tricks!"
                    )
                )
            )

            val intent = Intent(this, ItemListActivity::class.java)
            intent.putExtra("products", Gson().toJson(foodProductList))
            startActivity(intent)

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