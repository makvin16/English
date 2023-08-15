package com.zm.englishtraining.start_ui.fingerprints

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.zm.englishtraining.core.model.Ui
import com.zm.englishtraining.core.recycler.BaseFingerprint
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.start_ui.databinding.ItemTopicBinding
import com.zm.englishtraining.start_ui.model.TopicUi

class TopicFingerprint(
    context: Context,
    containerId: Int,
    private val onClickTopic: (TopicUi) -> Unit
) : BaseFingerprint<TopicUi, ItemTopicBinding>(context, containerId) {

    override fun isRelativeItem(item: Ui) = (item is TopicUi)

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<TopicUi, ItemTopicBinding> {
        val binding = ItemTopicBinding.inflate(layoutInflater, parent, false)
        return TopicViewHolder(
            context = context,
            binding = binding,
            onClickTopic = onClickTopic
        )
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<TopicUi>() {

        override fun areItemsTheSame(
            oldItem: TopicUi,
            newItem: TopicUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TopicUi,
            newItem: TopicUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}