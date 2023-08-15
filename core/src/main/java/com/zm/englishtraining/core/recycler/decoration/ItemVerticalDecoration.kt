package com.zm.englishtraining.core.recycler.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemVerticalDecoration(
    private val viewTypes: List<Int>,
    private val innerDivider: Int,
    private val outerDivider: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val childViewHolder = parent.getChildViewHolder(view)
        if (viewTypes.contains(childViewHolder.itemViewType).not()) return

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view).takeIf {
            it != RecyclerView.NO_POSITION
        } ?: RecyclerView.NO_POSITION

        if (currentPosition == RecyclerView.NO_POSITION) return

        val isReverse = (parent.layoutManager as LinearLayoutManager).reverseLayout
        val isPrevTargetView = adapter.isPrevTargetView(currentPosition, viewTypes, isReverse)
        val isNextTargetView = adapter.isNextTargetView(currentPosition, viewTypes, isReverse)

        with(outRect) {
            top = if (isPrevTargetView) innerDivider else outerDivider
            bottom = if (isNextTargetView) innerDivider else outerDivider
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(
        currentPosition: Int,
        viewTypes: List<Int>,
        isReverse: Boolean
    ): Boolean {
        return if (isReverse) {
            currentPosition != itemCount - 1 &&
                    viewTypes.contains(getItemViewType(currentPosition + 1))
        } else {
            currentPosition != 0 && viewTypes.contains(getItemViewType(currentPosition - 1))
        }
    }

    private fun RecyclerView.Adapter<*>.isNextTargetView(
        currentPosition: Int,
        viewTypes: List<Int>,
        isReverse: Boolean
    ): Boolean {
        return if (isReverse) {
            currentPosition != 0 && viewTypes.contains(getItemViewType(currentPosition - 1))
        } else {
            currentPosition != itemCount - 1 &&
                    viewTypes.contains(getItemViewType(currentPosition + 1))
        }
    }
}