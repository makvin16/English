package com.zm.englishtraining.game.model

import com.zm.englishtraining.core.model.Level
import com.zm.englishtraining.core.model.Ui

data class PhraseUi(
    val id: Long,
    val question: String,
    val answers: List<String>,
    val isAnswerVisible: Boolean,
    val level: Level
) : Ui