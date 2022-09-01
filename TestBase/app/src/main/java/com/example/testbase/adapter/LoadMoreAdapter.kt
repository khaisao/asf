package com.example.testbase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.testbase.databinding.ItemUserBinding
import com.example.testbase.model.User

class LoadMoreAdapter : BaseQuickAdapter<User, LoadMoreAdapter.VH>() {


    override fun onBindViewHolder(holder: VH, position: Int, item: User) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class VH(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item:User){
                with(binding){
                    ll.setOnClickListener {
                        Toast.makeText(binding.root.context, "rehreh", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}