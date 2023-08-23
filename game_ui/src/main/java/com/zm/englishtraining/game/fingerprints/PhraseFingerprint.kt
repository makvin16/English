package com.zm.englishtraining.game.fingerprints

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import com.zm.englishtraining.core.model.Ui
import com.zm.englishtraining.core.recycler.BaseFingerprint
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.game.model.PhraseUi
import com.zm.englishtraining.game_ui.databinding.ItemPhraseBinding

class PhraseFingerprint(
    context: Context,
    containerId: Int,
    private val onClickHelp: (PhraseUi) -> Unit,
    private val onClickConfirm: (String) -> Unit
) : BaseFingerprint<PhraseUi, ItemPhraseBinding>(context, containerId) {

    override fun isRelativeItem(item: Ui) = (item is PhraseUi)

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<PhraseUi, ItemPhraseBinding> {
        val binding = ItemPhraseBinding.inflate(layoutInflater, parent, false)
        return PhraseViewHolder(
            context = context,
            binding = binding,
            onClickHelp = onClickHelp,
            onClickConfirm = onClickConfirm
        )
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<PhraseUi>() {

        override fun areItemsTheSame(
            oldItem: PhraseUi,
            newItem: PhraseUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhraseUi,
            newItem: PhraseUi
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: PhraseUi,
            newItem: PhraseUi
        ): Any? {
            if (oldItem.isAnswerVisible != newItem.isAnswerVisible) {
                return newItem.isAnswerVisible
            }
            return super.getChangePayload(oldItem, newItem)
        }
    }
}