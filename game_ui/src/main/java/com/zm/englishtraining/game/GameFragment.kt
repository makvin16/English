package com.zm.englishtraining.game

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zm.englishtraining.core.base.BaseFragment
import com.zm.englishtraining.game_ui.databinding.FragmentGameBinding

class GameFragment : BaseFragment<FragmentGameBinding, GameNavigation>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGameBinding.inflate(inflater, container, false)

    override fun onViewCreated() {}
}