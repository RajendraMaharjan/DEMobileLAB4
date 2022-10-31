package com.rajendra.demobilelab4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajendra.demobilelab4.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAccount.setOnClickListener {
            val fName = binding.fNameET.editableText.toString().trim()
            val lName = binding.lNameET.editableText.toString().trim()
            val userName = binding.editTextEmail.editableText.toString().trim()
            val password = binding.editTextPassword.editableText.toString().trim()

            if (fName.isEmpty() || lName.isEmpty() || userName.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Fill All required Fields", Toast.LENGTH_SHORT).show()

            val returnData = Intent()
            returnData.putExtra("user", User(fName, lName, userName, password))
            setResult(RESULT_OK, returnData)
            finish()
        }
    }
}