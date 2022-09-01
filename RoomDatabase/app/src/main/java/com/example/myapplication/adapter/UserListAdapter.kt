package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.diff.UserItemCallBack
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.model.User

class UserListAdapter : ListAdapter<User, UserListAdapter.ViewHolder>(UserItemCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUser.text = user.toString()
        }
    }
}