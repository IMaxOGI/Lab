package com.example.firstlabwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.firstlabwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val words = this.resources.getStringArray(R.array.words).apply { shuffle() }
        var index = 0
        var currentWord = words[index]
        var shuffeledWord = String(currentWord.toCharArray().apply { shuffle() })
        binding.shuffledWordTextView.text = shuffeledWord
        binding.lvl.text = index.toString()

        binding.shuffledWordTextView.setOnKeyListener { _, keyCode, keyEvent ->
            if (
                (keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                if (binding.shuffledWordTextView.text.toString() == currentWord) {
                    Toast.makeText(baseContext, "True!", Toast.LENGTH_SHORT).show()
                    if (index == words.size - 1) {
                        Toast.makeText(baseContext, "Gongratz, words ended!", Toast.LENGTH_SHORT).show()
                        binding.shuffledWordTextView.text = ""

                        val intent = Intent(this, Task1Activity::class.java)
                        startActivity(intent)
                    } else {
                        index++
                        currentWord = words[index]
                        shuffeledWord = String(currentWord.toCharArray().apply { shuffle() })
                        binding.shuffledWordTextView.text = shuffeledWord
                    }
                    binding.inputField.text?.clear()
                } else {
                    Toast.makeText(baseContext, "False, try again!", Toast.LENGTH_SHORT).show()
                }
                true
            } else {
                false
            }
        }
    }
}