package com.devandreschavez.blogappgason.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.data.model.Post
import com.devandreschavez.blogappgason.databinding.PostItemViewBinding

class HomeAdapter(private val listPosts: List<Post>): RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PostViewHolder -> holder.bind(listPosts[position])
        }
    }
    override fun getItemCount(): Int = listPosts.size

    inner class PostViewHolder(private val binding: PostItemViewBinding): BaseViewHolder<Post>(binding.root){
        override fun bind(item: Post) {
            Glide.with(binding.root.context).load(item.profilePicture).into(binding.profilePicture)
            Glide.with(binding.root.context).load(item.postImage).into(binding.postImage)
            binding.profileName.text = item.profileName
            binding.postTimestamp.text = "Hace 2 horas"
        }

    }
}