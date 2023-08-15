package com.zm.englishtraining.core.model

sealed class ModeUi : Ui {

    object EnglishToRussian : ModeUi()

    object RussianToEnglish : ModeUi()
}