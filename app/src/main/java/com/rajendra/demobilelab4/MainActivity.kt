package com.rajendra.demobilelab4

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.rajendra.demobilelab4.activities.RegisterActivity
import com.rajendra.demobilelab4.activities.ShoppingCategory
import com.rajendra.demobilelab4.databinding.ActivityMainBinding
import com.rajendra.demobilelab4.model.Product
import com.rajendra.demobilelab4.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val userList = ArrayList<User>()
    private val foodProductList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList.addAll(
            arrayOf(
                User("RajKaiAdmin", "Mj", "raj@miu.edu", "rajendra"),
                User("RajOne", "Mj", "one@miu.edu", "one1"),
                User("RajTwo", "Mj", "two@miu.edu", "two2"),
                User("RajKaiThree", "Mj", "three@miu.edu", "three3"),
                User("RajKaiFour", "Mj", "four@miu.edu", "four4")
            )
        )
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


        binding.btnSignIn.setOnClickListener {
            val email = binding.editTextEmail.editableText.toString()
            val password = binding.editTextPassword.editableText.toString()

            if (email.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid username",
                Toast.LENGTH_SHORT
            ).show()
            else if (password.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid password",
                Toast.LENGTH_SHORT
            ).show()
            else {
                val user = User("", "", email, password)
                var found = false
                userList.forEach {
                    if (it == user) {
                        found = true
                        startActivity(
                            Intent(
                                this,
                                ShoppingCategory::class.java
                            ).apply {
                                putExtra("username", email)
                            })
                    }
                }

                if (!found) Toast.makeText(
                    this,
                    "Invalid User. Please Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.btnCreateAccount.setOnClickListener {
            resultLauncher.launch(Intent(this, RegisterActivity::class.java))
        }

        binding.textViewForgotPasswordTV.setOnClickListener {
            val email = binding.editTextEmail.editableText.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter username to change password.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            var password: String? = ""
            userList.forEach {
                if (it.userName == email) {
                    password = it.password
                    return@forEach
                }
            }

            if (password!!.isEmpty()) {
                Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mailto = "mailto:$email" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Change your Password.") +
                    "&body=" + Uri.encode("Your password is: $password")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: User = result.data?.extras?.get("user") as User
                userList.add(data)
            }
        }
}