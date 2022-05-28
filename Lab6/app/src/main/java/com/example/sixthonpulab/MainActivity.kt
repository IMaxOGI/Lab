package com.example.sixthonpulab

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sixthonpulab.databinding.ActivityMainBinding
import com.example.sixthonpulab.dialogs.FirstDialog
import com.example.sixthonpulab.generator.ElementGenerator
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: ElementGenerator

    private val NUMBER_OF_ITEMS: Int = 24
    private val COLUMNS = 4

    private val list: MutableList<MyComponent> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerViewContent()
    }

    private fun setRecyclerViewContent() {
        recyclerViewAdapter = ElementGenerator {
            val dialogFragment = FirstDialog(it)
            dialogFragment.show(supportFragmentManager, "some tag")
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMNS)
            adapter = recyclerViewAdapter
        }

        recyclerViewAdapter.submitList(setupData())
    }

    private fun setupData(): List<MyComponent> {
        val models = mutableListOf<MyComponent>()
        for (i in 1..NUMBER_OF_ITEMS) {
            models.add(
                MyComponent(
                    randNumber = Random.nextInt(99),
                    color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                )
            )
        }
        return models
    }

    data class MyComponent(
        val randNumber: Int,
        val color: Int
    )
}