package com.zm.englishtraining.core.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.zm.englishtraining.core.model.Ui

open class FingerprintAdapter(
    protected val fingerprints: List<ItemFingerprint<*, *>>,
): ListAdapter<Ui, BaseViewHolder<Ui, ViewBinding>>(
    FingerprintDiffUtil(fingerprints)
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Ui, ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerprints.find { itemFingerprint ->
            itemFingerprint.layoutId == viewType
        }
            ?.getViewHolder(inflater, parent)
            ?.let { baseViewHolder ->
                baseViewHolder as BaseViewHolder<Ui, ViewBinding>
            } ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Ui, ViewBinding>,
        position: Int
    ) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Ui, ViewBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerprints.find { itemFingerprint ->
            itemFingerprint.isRelativeItem(item)
        }?.layoutId ?: throw IllegalArgumentException("View type not found: $item")
    }
}