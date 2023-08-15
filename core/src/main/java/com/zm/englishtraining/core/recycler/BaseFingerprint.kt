package com.zm.englishtraining.core.recycler

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.zm.englishtraining.core.model.Ui

abstract class BaseFingerprint<I : Ui, V : ViewBinding>(
    protected val context: Context,
    protected val containerId: Int
) : ItemFingerprint<I, V> {

    override val layoutId: Int get() = containerId
}