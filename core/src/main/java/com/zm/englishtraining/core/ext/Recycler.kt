package com.zm.englishtraining.core.ext

import androidx.recyclerview.widget.RecyclerView
import com.zm.englishtraining.core.recycler.FingerprintAdapter

fun RecyclerView.setupRecyclerView(
    adapter: FingerprintAdapter?,
    layoutManager: RecyclerView.LayoutManager,
    listItemDecorations: List<RecyclerView.ItemDecoration>
) {
    apply {
        if (layoutManager.isAttachedToWindow) return@apply
        listItemDecorations.forEach {
            removeItemDecoration(it)
        }
        this.adapter = adapter
        this.layoutManager = layoutManager
        listItemDecorations.forEach {
            addItemDecoration(it)
        }
    }
}