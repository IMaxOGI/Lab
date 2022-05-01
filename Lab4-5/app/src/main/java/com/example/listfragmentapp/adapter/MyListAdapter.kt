package com.example.listfragmentapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listfragmentapp.MainActivity
import com.example.listfragmentapp.R
import com.example.listfragmentapp.model.User

class MyListAdapter ( private val inflater : LayoutInflater) :
    ListAdapter<User, MyListAdapter.ViewHolder>((MyCallback())) {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.job)
        private val email = view.findViewById<TextView>(R.id.email)

        fun bind (user: User) {
            name.text = user.name
            age.text = user.job
            email.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.ViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyListAdapter.ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class MyCallback : DiffUtil.ItemCallback<User> () {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.job == newItem.job && oldItem.email == newItem.email
        }
    }

}