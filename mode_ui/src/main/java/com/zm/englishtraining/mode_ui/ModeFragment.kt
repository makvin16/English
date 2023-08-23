package com.zm.englishtraining.mode_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zm.englishtraining.core.base.BaseFragment
import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.mode_ui.databinding.FragmentModeBinding

class ModeFragment : BaseFragment<FragmentModeBinding, ModeNavigation>(), IModeFragment {

    override val argCategoriesIds: LongArray
        get() = arguments?.getLongArray(ARG_CATEGORIES_IDS) ?: error("array")

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentModeBinding.inflate(inflater, container, false)

    override fun onViewCreated() = with(binding) {
        setupClickListener(btnEngToRus, btnRusToEng)
    }

    override fun onClick(v: View?) = with(binding) {
        when (v?.id) {
            btnEngToRus.id -> {
                navigation.navigateToGame(argCategoriesIds, ModeUi.EnglishToRussian)
            }
            btnRusToEng.id -> {
                navigation.navigateToGame(argCategoriesIds, ModeUi.RussianToEnglish)
            }
        }
    }

    companion object {
        private const val ARG_CATEGORIES_IDS = "categoriesIds"

        fun makeArgs(ids: LongArray): Bundle {
            return Bundle(1).apply {
                putLongArray(ARG_CATEGORIES_IDS, ids)
            }
        }

    }
}