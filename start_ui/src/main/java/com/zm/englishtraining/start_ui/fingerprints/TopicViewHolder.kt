package com.zm.englishtraining.start_ui.fingerprints

import android.content.Context
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.start_ui.databinding.ItemTopicBinding
import com.zm.englishtraining.start_ui.model.TopicUi

class TopicViewHolder(
    context: Context,
    binding: ItemTopicBinding,
    onClickTopic: (TopicUi) -> Unit
) : BaseViewHolder<TopicUi, ItemTopicBinding>(context, binding) {

    init {
        binding.root.setOnClickListener {
            item?.let(onClickTopic)
        }
    }

    override fun onBind(item: TopicUi) = with(binding) {
        super.onBind(item)
        textView.text = item.name
        checkbox.isChecked = item.isChoose
    }
}