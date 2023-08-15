package com.zm.englishtraining.core.recycler

import androidx.recyclerview.widget.DiffUtil
import com.zm.englishtraining.core.model.Ui

class FingerprintDiffUtil(
    private val fingerprints: List<ItemFingerprint<*, *>>
): DiffUtil.ItemCallback<Ui>() {

    override fun areItemsTheSame(oldItem: Ui, newItem: Ui): Boolean {
        if (oldItem::class != newItem::class) return false
        return getItemCallback(oldItem).areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Ui, newItem: Ui): Boolean {
        if (oldItem::class != newItem::class) return false
        return getItemCallback(oldItem).areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: Ui, newItem: Ui): Any? {
        if (oldItem::class != newItem::class) return false
        return getItemCallback(oldItem).getChangePayload(oldItem, newItem)
    }

    private fun getItemCallback(
        item: Ui
    ): DiffUtil.ItemCallback<Ui> = fingerprints.find { it.isRelativeItem(item) }
        ?.getDiffUtil()
        ?.let { it as DiffUtil.ItemCallback<Ui> }
        ?: throw IllegalStateException("DiffUtil not found for $item")
}