package com.example.listfragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.listfragmentapp.databinding.ActivityMainBinding
import com.example.listfragmentapp.fragments.ListFragment
import com.example.listfragmentapp.fragments.SecondFragment
import com.example.listfragmentapp.fragments.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.pager.adapter = MyFragmentAdapter(this,
        ListFragment(), SecondFragment(), ThirdFragment()
        )

        TabLayoutMediator(binding.tabLayout, binding.pager) {tab, pos ->
            tab.text = "Fragment $pos"
        }.attach()
    }

    class MyFragmentAdapter (activity: AppCompatActivity, first: ListFragment, second: SecondFragment, third: ThirdFragment) : FragmentStateAdapter (activity) {
        private val list = arrayOf(
            first, second, third
        )

        override fun getItemCount(): Int = list.size

        override fun createFragment(position: Int) = list[position]
    }
}