package com.example.homewordretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homewordretrofit.callback.OnMusicItemClick
import com.example.homewordretrofit.databinding.ItemMusicBinding
import com.example.homewordretrofit.model.Music
import com.example.homewordretrofit.model.MusicX

class MusicAdapter(
    private val listMusic:List<MusicX>,
    private val callback: OnMusicItemClick
    ) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMusicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMusic[position])
    }

    override fun getItemCount(): Int {
        return listMusic.size
    }
    inner class ViewHolder(private val binding:ItemMusicBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(musics:MusicX){
            binding.tvName.text = musics.title
            binding.tvDes.text = musics.artist
            Glide
                .with(binding.imgMusic)
                .load(musics.image)
                .into(binding.imgMusic)
            binding.itemMusic.setOnClickListener {
                callback.onClick(musics)
            }
        }
    }
}