package com.zm.englishtraining.core.navigator

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigator

interface Navigator {

    val savedStateHandle: SavedStateHandle?

    fun observeResult(result: (Bundle) -> Unit)

    fun removeObserve(result: (Bundle) -> Unit)

    fun navigate(actionId: Int)

    fun navigateWithData(actionId: Int, data: Bundle)

    fun navigateWithExtras(actionId: Int, extras: Navigator.Extras)

    fun navigateBack()

    fun navigateBackWithResult(result: Bundle)
}