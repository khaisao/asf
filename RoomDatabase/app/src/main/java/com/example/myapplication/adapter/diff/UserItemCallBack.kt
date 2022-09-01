package com.example.myapplication.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.model.User

object UserItemCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}