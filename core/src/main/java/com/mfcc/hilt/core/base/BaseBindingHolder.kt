package com.mfcc.hilt.core.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView



abstract class BaseBindingHolder<T : ViewDataBinding, B>(viewItem : View) :
    RecyclerView.ViewHolder(viewItem) {

    val binding: T

    init {
        binding = DataBindingUtil.bind(itemView)!!
    }

    abstract fun bind(params : B)
}