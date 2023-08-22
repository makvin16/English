package com.zm.englishtraining.start_ui.fingerprints

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zm.englishtraining.core.model.Ui
import com.zm.englishtraining.core.recycler.BaseFingerprint
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.start_ui.databinding.ItemCategoryBinding
import com.zm.englishtraining.start_ui.model.CategoryUi

class CategoryFingerprint(
    context: Context,
    containerId: Int,
    private val onClickTopic: (CategoryUi) -> Unit
) : BaseFingerprint<CategoryUi, ItemCategoryBinding>(context, containerId) {

    override fun isRelativeItem(item: Ui) = (item is CategoryUi)

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CategoryUi, ItemCategoryBinding> {
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(
            context = context,
            binding = binding,
            onClickTopic = onClickTopic
        )
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CategoryUi>() {

        override fun areItemsTheSame(
            oldItem: CategoryUi,
            newItem: CategoryUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryUi,
            newItem: CategoryUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}