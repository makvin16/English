package com.zm.englishtraining.start_ui

import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory

interface StartProvider {
    val factoryStart: ViewModelAssistedFactory<StartViewModel>
}