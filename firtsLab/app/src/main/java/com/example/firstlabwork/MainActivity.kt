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
        var shuffle = String(currentWord.toCharArray().apply { shuffle() })
        val lvl = binding.lvl
        binding.t2TvWord.text = shuffle


        binding.t2Input.setOnKeyListener { _, keyCode, keyEvent ->
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                if (binding.t2Input.text.toString() == currentWord) {
                    Toast.makeText(baseContext, "True!", Toast.LENGTH_SHORT).show()
                    if(index == words.size - 1) {
                        Toast.makeText(baseContext, "You win!", Toast.LENGTH_SHORT).show()
                        binding.t2TvWord.text = ""

                        val intent = Intent(this, Task1Activity::class.java)
                        startActivity(intent)
                    } else {
                        index++
                        lvl.text = "Level " + (index+1)
                        currentWord = words[index]
                        shuffle = String(currentWord.toCharArray().apply { shuffle() })
                        binding.t2TvWord.text = shuffle
                    }
                    binding.t2Input.text?.clear()
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