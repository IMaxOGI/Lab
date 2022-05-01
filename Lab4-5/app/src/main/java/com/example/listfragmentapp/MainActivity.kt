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
        val list = arrayListOf(
            ListFragment(), SecondFragment(), ThirdFragment()
        )
        binding.pager.adapter = MyFragmentAdapter(this, list
        )

        TabLayoutMediator(binding.tabLayout, binding.pager) {tab, pos ->
            tab.text = "Fragment $pos"
        }.attach()
    }

    class MyFragmentAdapter(activity: AppCompatActivity, list: ArrayList<Fragment>) : FragmentStateAdapter (activity) {
//        private val list = arrayOf(
//            first, second, third
//        )
        private val fragmentList = list
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int) = fragmentList[position]
    }
}