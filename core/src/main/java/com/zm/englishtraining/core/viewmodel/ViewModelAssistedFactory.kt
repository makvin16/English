package com.zm.englishtraining.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface ViewModelAssistedFactory<VM : ViewModel> : ViewModelProvider.Factory {
    fun create(savedStateHandle: SavedStateHandle): VM
}