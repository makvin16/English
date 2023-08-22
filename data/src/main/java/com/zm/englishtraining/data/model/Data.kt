package com.zm.englishtraining.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val name: String,
    val phrases: List<Phrase>
) {

    @JsonClass(generateAdapter = true)
    data class Phrase(
        val content: String,
        val translates: List<String>
    )

    companion object {

        fun Data.toCategoryEntity(): CategoryEntity {
            return CategoryEntity(name = name)
        }

        fun Phrase.toPhraseEntity(categoryId: Long): PhraseEntity {
            return PhraseEntity(content = content, categoryId = categoryId)
        }

        fun Phrase.toTranslatesEntity(phraseId: Long): List<TranslateEntity> {
            return translates.map {
                TranslateEntity(content = it, phraseId = phraseId)
            }
        }
    }
}