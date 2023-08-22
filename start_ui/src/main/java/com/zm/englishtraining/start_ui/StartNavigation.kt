package com.zm.englishtraining.start_ui

import com.zm.englishtraining.core.navigator.Navigation

interface StartNavigation : Navigation {

    fun navigateToMode(topicIds: LongArray)
}