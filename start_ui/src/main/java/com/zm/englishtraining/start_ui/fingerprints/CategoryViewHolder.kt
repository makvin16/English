package com.zm.englishtraining.start_ui.fingerprints

import android.content.Context
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.start_ui.databinding.ItemCategoryBinding
import com.zm.englishtraining.start_ui.model.CategoryUi

class CategoryViewHolder(
    context: Context,
    binding: ItemCategoryBinding,
    onClickCategory: (CategoryUi) -> Unit
) : BaseViewHolder<CategoryUi, ItemCategoryBinding>(context, binding) {

    init {
        binding.root.setOnClickListener {
            item?.let(onClickCategory)
        }
    }

    override fun onBind(item: CategoryUi) = with(binding) {
        super.onBind(item)
        textView.text = item.name
        checkbox.isChecked = item.isChoose
    }
}