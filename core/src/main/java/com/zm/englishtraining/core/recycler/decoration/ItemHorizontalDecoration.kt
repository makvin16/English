package com.zm.englishtraining.core.recycler.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemHorizontalDecoration(
    private val viewTypes: List<Int>,
    private val divider: Int = 0,
    private val leftDivider: Int? = null,
    private val rightDivider: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (viewTypes.contains(parent.getChildViewHolder(view).itemViewType).not()) {
            return
        }

        with(outRect) {
            left = leftDivider ?: divider
            right = rightDivider ?: divider
        }
    }
}