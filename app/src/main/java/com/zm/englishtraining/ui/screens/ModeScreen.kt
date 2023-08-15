package com.zm.englishtraining.ui.screens

import android.os.Bundle
import androidx.navigation.NavController
import com.zm.englishtraining.R
import com.zm.englishtraining.mode_ui.ModeNavigation

class ModeScreen(navController: NavController) : Screen(navController), ModeNavigation {

    override fun navigateToGame() {
        val data = Bundle()
        navigateWithData(R.id.action_mode_to_game, data)
    }
}