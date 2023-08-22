package com.zm.englishtraining.mode_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zm.englishtraining.core.base.BaseFragment
import com.zm.englishtraining.mode_ui.databinding.FragmentModeBinding

class ModeFragment : BaseFragment<FragmentModeBinding, ModeNavigation>(), IModeFragment {

    override val argTopicIds: IntArray
        get() = arguments?.getIntArray(ARG_TOPIC_IDS) ?: error("array")

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentModeBinding.inflate(inflater, container, false)

    override fun onViewCreated() = with(binding) {
        setupClickListener(btnEngToRus, btnRusToEng)
        showToast(argTopicIds.contentToString())
    }

    override fun onClick(v: View?) = with(binding) {
        when (v?.id) {
            btnEngToRus.id -> {
                navigation.navigateToGame()
            }
            btnRusToEng.id -> {
                navigation.navigateToGame()
            }
        }
    }

    companion object {
        private const val ARG_TOPIC_IDS = "topicIds"

        fun makeArgs(ids: LongArray): Bundle {
            return Bundle(1).apply {
//                putIntArray(ARG_TOPIC_IDS, ids)
            }

        }

    }
}