package com.zm.englishtraining.mode_ui

import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.core.navigator.Navigation

interface ModeNavigation : Navigation {

    fun navigateToGame(ids: LongArray, mode: ModeUi)
}