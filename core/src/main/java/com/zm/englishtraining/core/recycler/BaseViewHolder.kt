package com.zm.englishtraining.core.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.zm.englishtraining.core.model.Ui

abstract class BaseViewHolder<I : Ui, V : ViewBinding>(
    protected val context: Context,
    protected val binding: V,
): RecyclerView.ViewHolder(binding.root) {

    protected val tag = "ViewHolder"
    var item: I? = null

    open fun onBind(item: I) {
        this.item = item
    }

    open fun onBind(item: I, payloads: List<Any>) {
        this.item = item
    }
}