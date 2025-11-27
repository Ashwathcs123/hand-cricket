package com.ashwath.handcricket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.ashwath.handcricket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onResume() {
        super.onResume()
        binding.etName.setText("")
    }

    private fun init() {
        binding.etName.addTextChangedListener {
            binding.tlNameContainer.error = ""
        }
        binding.btStart.setOnClickListener {
            checkForEmailValidation()
        }
    }

    private fun checkForEmailValidation() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            binding.tlNameContainer.error = "Please Enter Your name"
            return
        }

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("USER_NAME", name)
        startActivity(intent)
    }
}