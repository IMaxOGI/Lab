package com.example.listfragmentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listfragmentapp.R
import com.example.listfragmentapp.adapter.MyListAdapter
import com.example.listfragmentapp.databinding.FragmentListBinding
import com.example.listfragmentapp.model.User
import kotlin.random.Random

class ListFragment : Fragment (R.layout.fragment_list) {
    private lateinit var binding : FragmentListBinding
    private val list: MutableList<User> = mutableListOf()
    private val userList: List<User> = arrayListOf(
        User("Tom", "tom@domain.com", "Waiter"),
        User("Jane", "jane@domain.com", "HR"),
        User("Bill", "bill@domain.com", "Architect"),
        User("Max", "max@domain.com", "Fronted Junior Developer"),
        User("Sam", "sam@domain.com", "Java Software Developer")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MyListAdapter(layoutInflater)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.submitList(null)

        binding.add.setOnClickListener {
            val randInt = Random.nextInt(0, 5)
            list.add(userList[randInt])

            adapter.submitList(list.toList())
        }
//        binding.buttonRemove.setOnClickListener {
//            if (list.isNotEmpty()) {
//                list.removeLast()
//                adapter.submitList(list.toList())
//            }
//        }

        binding.clear.setOnClickListener {
            list.clear()
            adapter.submitList(list.toList())
        }
    }
}