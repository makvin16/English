package com.zm.englishtraining.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zm.englishtraining.core.model.EmptyUi

abstract class BaseViewModel : ViewModel() {

    protected val showProgress = MutableLiveData<EmptyUi>()
    val eventShowProgress: LiveData<EmptyUi> = showProgress

    protected val hideProgress = MutableLiveData<EmptyUi>()
    val eventHideProgress: LiveData<EmptyUi> = hideProgress
}