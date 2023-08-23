package com.zm.englishtraining.ui.screens

import androidx.navigation.NavController
import com.zm.englishtraining.R
import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.game.GameFragment
import com.zm.englishtraining.mode_ui.ModeNavigation

class ModeScreen(navController: NavController) : Screen(navController), ModeNavigation {

    override fun navigateToGame(ids: LongArray, mode: ModeUi) {
        val data = GameFragment.makeArgs(ids, mode)
        navigateWithData(R.id.action_mode_to_game, data)
    }
}