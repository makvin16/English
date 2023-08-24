package com.zm.englishtraining.game.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.zm.englishtraining.core.base.BaseDialogFragment
import com.zm.englishtraining.game_ui.R
import com.zm.englishtraining.game_ui.databinding.DialogFragmentResultBinding

class ResultDialogFragment : BaseDialogFragment<DialogFragmentResultBinding>(),
    IResultDialogFragment {

    override val argIsCorrect: Boolean
        get() = arguments?.getBoolean(ARG_IS_CORRECT) ?: error("is correct")

    override val argCorrectAnswer: String
        get() = arguments?.getString(ARG_CORRECT_ANSWER) ?: error("correct answer")

    override val argActualAnswer: String
        get() = arguments?.getString(ARG_ACTUAL_ANSWER) ?: error("actual answer")

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogFragmentResultBinding.inflate(inflater, container, false)

    override fun onViewCreated() = with(binding) {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val image = if (argIsCorrect) {
            com.zm.englishtraining.core_ui.R.drawable.ic_correct
        } else {
            com.zm.englishtraining.core_ui.R.drawable.ic_incorrect
        }
        imageView.setImageResource(image)
        textViewCorrectAnswer.text = argCorrectAnswer
        textViewActualAnswer.text = argActualAnswer
        btnNext.setOnClickListener {
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf())
            dismiss()
        }
    }

    companion object {
        const val REQUEST_KEY = "resultRequestKey"

        @JvmStatic
        private val TAG = ResultDialogFragment::class.java.simpleName
        private const val ARG_IS_CORRECT = "isCorrect"
        private const val ARG_CORRECT_ANSWER = "correctAnswer"
        private const val ARG_ACTUAL_ANSWER = "actualAnswer"

        fun show(
            fragmentManager: FragmentManager,
            isCorrect: Boolean,
            correctAnswer: String,
            actualAnswer: String
        ) {
            if (fragmentManager.findFragmentByTag(TAG) != null) return
            val resultDialogFragment = ResultDialogFragment()
            resultDialogFragment.isCancelable = false
            resultDialogFragment.arguments = Bundle(2).apply {
                putBoolean(ARG_IS_CORRECT, isCorrect)
                putString(ARG_CORRECT_ANSWER, correctAnswer)
                putString(ARG_ACTUAL_ANSWER, actualAnswer)
            }
            resultDialogFragment.show(fragmentManager, TAG)
        }
    }
}