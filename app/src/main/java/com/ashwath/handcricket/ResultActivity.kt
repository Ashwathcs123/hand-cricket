package com.ashwath.handcricket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ashwath.handcricket.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val userScore = intent.getIntExtra("USER_TOTAL_SCORE" , 0)
        val systemScore = intent.getIntExtra("SYSTEM_TOTAL_SCORE" , 0)

        if(userScore > systemScore) {
            binding.tvResult.text = getString(R.string.win)
        } else if (userScore < systemScore) {
            binding.tvResult.text = getString(R.string.loose)
        } else {
            binding.tvResult.text = getString(R.string.draw)
        }

        binding.btClose.setOnClickListener {
            finish()
        }
    }
}