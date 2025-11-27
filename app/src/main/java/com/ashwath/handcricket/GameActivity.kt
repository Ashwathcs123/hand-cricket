package com.ashwath.handcricket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ashwath.handcricket.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var userTotalScore = 0
    private var systemTotalScore = 0
    private var userPlay = BATTING

    companion object {
        const val BATTING = "batting"
        const val BOWLING = "bowling"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.tvUserName.text = intent.getStringExtra("USER_NAME") ?: "Player"
        buttonClick()
        resetScores()
    }

    private fun buttonClick() {
        binding.btOne.setOnClickListener {
            onUserMove(1)
        }
        binding.btTwo.setOnClickListener {
            onUserMove(2)
        }
        binding.btThree.setOnClickListener {
            onUserMove(3)
        }
        binding.btFour.setOnClickListener {
            onUserMove(4)
        }
        binding.btFive.setOnClickListener {
            onUserMove(5)
        }
        binding.btSix.setOnClickListener {
            onUserMove(6)
        }
    }

    private fun onUserMove(userScore: Int) {

        val systemScore = (1..6).random()
        binding.tvUserScore.text = userScore.toString()
        binding.tvSystemScore.text = systemScore.toString()

        if(userScore == systemScore) {
            handleOut()
            return
        }

        updateRunningScore(userScore, systemScore)
    }

    private fun handleOut() {
        if(userPlay == BATTING) {
            binding.tvUserLabel.text = BOWLING
            binding.tvSystemLabel.text = BATTING
            userPlay = BOWLING
            showOutAnimation()
            resetScores()
            binding.tvTotalUserScore.text = userTotalScore.toString()
        } else { goToResultScreen() }
    }

    private fun showOutAnimation() {
        binding.tvOut.isVisible = true
        binding.tvOut.postDelayed({
            binding.tvOut.isVisible = false
        }, 1500)
    }

    private fun resetScores() {
        binding.tvTotalUserScore.text = "0"
        binding.tvScore.text = "0"
        binding.tvUserScore.text = "0"
        binding.tvSystemScore.text = "0"
    }

    private fun goToResultScreen() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("USER_TOTAL_SCORE", userTotalScore)
        intent.putExtra("SYSTEM_TOTAL_SCORE", systemTotalScore)
        startActivity(intent)
        finish()
        return
    }

    private fun updateRunningScore(userScore: Int, systemScore: Int) {
        if(userPlay == BATTING) {
            userTotalScore += userScore
            binding.tvScore.text = userTotalScore.toString()
        } else {
            systemTotalScore += systemScore
            binding.tvScore.text = systemTotalScore.toString()
        }
    }
}