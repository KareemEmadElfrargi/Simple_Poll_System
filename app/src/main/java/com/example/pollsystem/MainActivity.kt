package com.example.pollsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pollsystem.databinding.ActivityMainBinding

data class OptionState(var count: Double, var isSelected: Boolean)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var option1State = OptionState(0.0, true)
    private var option2State = OptionState(0.0, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBarTwo.setOnTouchListener { _, _ ->
            true
        }

        binding.optionOne.setOnClickListener {
            handleOptionClick(option1State, option2State)
        }

        binding.optionTwo.setOnClickListener {
            handleOptionClick(option2State, option1State)
        }
    }

    private fun handleOptionClick(selectedOption: OptionState, otherOption: OptionState) {
        if (selectedOption.isSelected) {
            selectedOption.count++
            otherOption.count = 1.0
            otherOption.isSelected = true
            selectedOption.isSelected = false
        } else {
            selectedOption.count = 1.0
            otherOption.count++
            selectedOption.isSelected = true
            otherOption.isSelected = false
        }

        calculatePercent()
    }

    private fun calculatePercent() {
        val total = option1State.count + option2State.count
        val percent1 = (option1State.count / total) * 100
        val percent2 = (option2State.count / total) * 100

        binding.percentOne.text = String.format("%.0f%%", percent1)
        binding.seekBarOne.progress = percent1.toInt()
        binding.percentTwo.text = String.format("%.0f%%", percent2)
        binding.seekBarTwo.progress = percent2.toInt()
    }
}
