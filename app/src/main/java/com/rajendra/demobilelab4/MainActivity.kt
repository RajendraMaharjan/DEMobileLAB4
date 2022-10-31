package com.rajendra.demobilelab4

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.rajendra.demobilelab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val userList = ArrayList<User>()

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