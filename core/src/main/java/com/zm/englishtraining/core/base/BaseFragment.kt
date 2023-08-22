package com.zm.englishtraining.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.zm.englishtraining.core.model.EmptyUi
import com.zm.englishtraining.core.navigator.Navigation
import com.zm.englishtraining.core.navigator.NavigatorProvider

abstract class BaseFragment<B : ViewBinding, N : Navigation> : Fragment(), IBaseFragment,
    IBaseFragment.Events, View.OnClickListener {

    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    abstract fun onViewCreated()

    private var _binding: B? = null
    val binding: B get() = _binding ?: error("binding exception")

    private var _navigation: N? = null
    val navigation: N get() = _navigation ?: error("navigation exception")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated()
        onInitObservers()
    }

    override fun onStart() {
        val navigator = (requireActivity() as NavigatorProvider).navigator
        @Suppress("UNCHECKED_CAST")
        _navigation = navigator as N
        super.onStart()
    }

    override fun setupClickListener(vararg views: View) {
        views.forEach { view ->
            view.setOnClickListener(this)
        }
    }

    override fun onInitObservers() = Unit

    override fun onClick(v: View?) = Unit

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(messageId: Int) {
        Toast.makeText(requireContext(), getString(messageId), Toast.LENGTH_SHORT).show()
    }

    protected inline fun <T> observe(
        liveData: LiveData<T>,
        crossinline action: (t: T) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner, Observer {
            it?.let { t ->
                action(t)
            }
        })
    }

    protected fun ViewGroup.visibilitiesExcept(
        isVisible: Boolean,
        vararg exceptIds: View
    ) {
        children.forEach { view ->
            if (view !is Space && view !is Group && exceptIds.contains(view).not()) {
                view.isVisible = isVisible
            }
        }
    }

    override fun onShowLoading(empty: EmptyUi) = Unit

    override fun onHideLoading(empty: EmptyUi) = Unit
}