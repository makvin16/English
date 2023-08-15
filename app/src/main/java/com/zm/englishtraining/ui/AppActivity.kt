package com.zm.englishtraining.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.zm.englishtraining.R
import com.zm.englishtraining.core.navigator.Navigator
import com.zm.englishtraining.core.navigator.NavigatorProvider
import com.zm.englishtraining.ui.screens.Screen

class AppActivity : AppCompatActivity(R.layout.activity_app), NavigatorProvider,
    NavController.OnDestinationChangedListener {

    private var _navController: NavController? = null
    private val navController: NavController get() = _navController ?: error("nav controller")

    private var _navigator: Navigator? = null
    override val navigator: Navigator get() = _navigator ?: error("navigator exception")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(application as App).appComponent.inject(this)
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = navHost.navController
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        _navigator = Screen.build(
            selfNavController = controller,
            navController = navController,
            destination = destination
        )
    }
}