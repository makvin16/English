package com.zm.englishtraining.ui.screens

import androidx.navigation.NavController
import com.zm.englishtraining.R
import com.zm.englishtraining.mode_ui.ModeFragment
import com.zm.englishtraining.start_ui.StartNavigation

class StartScreen(navController: NavController) : Screen(navController), StartNavigation {

    override fun navigateToMode(topicIds: IntArray) {
        val data = ModeFragment.makeArgs(topicIds)
        navigateWithData(R.id.action_start_to_mode, data)
    }
}