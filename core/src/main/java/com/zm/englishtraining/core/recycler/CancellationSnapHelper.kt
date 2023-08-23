package com.zm.englishtraining.core.recycler

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

class CancellationSnapHelper : LinearSnapHelper() {

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        if (layoutManager == null) return RecyclerView.NO_POSITION
        val view = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val position = layoutManager.getPosition(view)
        val targetPosition = if (velocityX < 0) {
            position - 1
        } else {
            position + 1
        }
        return min( layoutManager.itemCount - 1, max(targetPosition, 0))
    }
}