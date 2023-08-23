package com.zm.englishtraining.game.fingerprints

import android.content.Context
import android.os.Build
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.zm.englishtraining.core.recycler.BaseViewHolder
import com.zm.englishtraining.game.model.PhraseUi
import com.zm.englishtraining.game_ui.databinding.ItemPhraseBinding

@RequiresApi(Build.VERSION_CODES.CUPCAKE)
class PhraseViewHolder(
    context: Context,
    binding: ItemPhraseBinding,
    onClickHelp: (PhraseUi) -> Unit,
    onClickConfirm: (String) -> Unit
) : BaseViewHolder<PhraseUi, ItemPhraseBinding>(context, binding) {

    init {
        binding.imageViewHelp.setOnClickListener {
            item?.let(onClickHelp)
        }
        binding.btnAnswer.setOnClickListener {
            onClickConfirm(binding.editTextAnswer.text.toString())
            binding.editTextAnswer.setText("")
        }
        binding.editTextAnswer.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClickConfirm(binding.editTextAnswer.text.toString())
                binding.editTextAnswer.setText("")
            }
            return@setOnEditorActionListener true
        }
        binding.textViewQuestion.setOnClickListener {
            Toast.makeText(context, "${item?.level?.value}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(item: PhraseUi) = with(binding) {
        super.onBind(item)
        textViewQuestion.text = item.question
        textViewAnswer.text = item.answers.toString()
        textViewAnswer.isVisible = item.isAnswerVisible
        imageViewHelp.isVisible = item.isAnswerVisible.not()
    }

    override fun onBind(item: PhraseUi, payloads: List<Any>) = with(binding) {
        super.onBind(item, payloads)
        textViewAnswer.isVisible = item.isAnswerVisible
        imageViewHelp.isVisible = item.isAnswerVisible.not()
    }
}