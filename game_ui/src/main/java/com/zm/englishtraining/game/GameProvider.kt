package com.zm.englishtraining.game

import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory

interface GameProvider {
    fun factoryGame(ids: LongArray, mode: ModeUi): ViewModelAssistedFactory<GameViewModel>
}