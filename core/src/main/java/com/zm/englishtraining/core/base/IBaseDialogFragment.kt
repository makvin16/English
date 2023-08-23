package com.zm.englishtraining.core.base

import android.view.View
import com.zm.englishtraining.core.model.EmptyUi

interface IBaseDialogFragment {

    fun setupClickListener(vararg views: View)

    fun onInitObservers()

    interface Events {

        fun onDismiss(empty: EmptyUi)
    }
}