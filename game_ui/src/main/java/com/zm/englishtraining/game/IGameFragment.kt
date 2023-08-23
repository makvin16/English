package com.zm.englishtraining.game

import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.game.model.PhraseUi

interface IGameFragment {

    val argCategoriesIds: LongArray

    val argMode: ModeUi

    fun onUpdatePhrases(phrases: List<PhraseUi>)

    fun onClickHelp(phrase: PhraseUi)

    fun onClickConfirm(answer: String)

    fun onScroll(pos: Int)
}