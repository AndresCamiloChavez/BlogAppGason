package com.devandreschavez.blogappgason.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}