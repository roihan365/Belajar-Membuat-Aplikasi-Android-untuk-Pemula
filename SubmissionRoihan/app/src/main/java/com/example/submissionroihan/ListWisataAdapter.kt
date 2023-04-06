package com.example.submissionroihan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionroihan.databinding.ItemRowWisataBinding

class ListWisataAdapter(private val listWisata: ArrayList<Wisata>) : RecyclerView.Adapter<ListWisataAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallBack
    interface OnItemClickCallBack {
        fun onItemClicked(data: Wisata)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(var binding: ItemRowWisataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listWisata.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, lokasi, photo) = listWisata[position]
        holder.binding.imgWisata.setImageResource(photo)
        holder.binding.tvNamaWisata.text = name
        holder.binding.tvLokasi.text = lokasi
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(listWisata[holder.adapterPosition])
        }
    }
}