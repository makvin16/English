package com.zm.englishtraining.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ModeUi : Ui, Parcelable {

    @Parcelize
    object EnglishToRussian : ModeUi()

    @Parcelize
    object RussianToEnglish : ModeUi()
}