package com.zm.englishtraining.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.zm.englishtraining.core.model.EmptyUi

abstract class BaseDialogFragment<B : ViewBinding> : DialogFragment(), IBaseDialogFragment,
    IBaseDialogFragment.Events, View.OnClickListener {

    private var _binding: B? = null
    protected val binding get() = _binding ?: error("binding exception")

    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    abstract fun onViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated()
        onInitObservers()
    }

    override fun onDismiss(empty: EmptyUi) {
        dismiss()
    }

    override fun onClick(v: View?) = Unit

    override fun setupClickListener(vararg views: View) {
        views.forEach { view ->
            view.setOnClickListener(this)
        }
    }

    override fun onInitObservers() = Unit
}