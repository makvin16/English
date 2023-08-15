package com.zm.englishtraining.ui.screens

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.zm.englishtraining.R
import com.zm.englishtraining.core.navigator.Navigator

abstract class Screen(
    private val navController: NavController
) : Navigator {

    override val savedStateHandle: SavedStateHandle?
        get() = navController.currentBackStackEntry?.savedStateHandle

    override fun observeResult(result: (Bundle) -> Unit) {
        navController.currentBackStackEntry?.let {
            savedStateHandle?.getLiveData<Bundle>(KEY_RESULT)?.observe(it) { result ->
                result(result)
                it.savedStateHandle.remove<Bundle>(KEY_RESULT)
            }
        }
    }

    override fun removeObserve(result: (Bundle) -> Unit) {
        savedStateHandle?.getLiveData<Bundle>(KEY_RESULT)?.removeObserver(result)
        navController.currentBackStackEntry?.let {
            savedStateHandle?.getLiveData<Bundle>(KEY_RESULT)?.removeObservers(it)
        }
    }

    override fun navigate(actionId: Int) {
        navController.navigate(actionId)
    }

    override fun navigateWithData(actionId: Int, data: Bundle) {
        navController.navigate(actionId, data)
    }

    override fun navigateWithExtras(
        actionId: Int,
        extras: androidx.navigation.Navigator.Extras
    ) {
        navController.navigate(actionId, null, null, extras)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateBackWithResult(result: Bundle) {
        navController.previousBackStackEntry?.savedStateHandle?.set(KEY_RESULT, result)
        navController.navigateUp()
    }

    companion object {
        private const val KEY_RESULT = "result"

        fun build(
            selfNavController: NavController,
            navController: NavController,
            destination: NavDestination
        ): Screen {
            return when (destination.id) {
                R.id.start -> {
                    StartScreen(navController)
                }
                R.id.mode -> {
                    ModeScreen(navController)
                }
                R.id.game -> {
                    GameScreen(navController)
                }
                else -> error("${destination.label} is absent")
            }
        }
    }
}