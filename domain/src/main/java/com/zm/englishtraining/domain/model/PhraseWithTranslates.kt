package com.zm.englishtraining.domain.model

data class PhraseWithTranslates(
    val phraseId: Long,
    val phrase: String,
    val translates: List<String>
)