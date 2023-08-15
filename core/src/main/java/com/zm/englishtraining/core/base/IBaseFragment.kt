package com.zm.englishtraining.core.base

import android.view.View
import com.zm.englishtraining.core.model.EmptyUi

interface IBaseFragment {

    fun setupClickListener(vararg views: View)

    fun onInitObservers()

    interface Events {

        fun onShowLoading(empty: EmptyUi)

        fun onHideLoading(empty: EmptyUi)
    }
}