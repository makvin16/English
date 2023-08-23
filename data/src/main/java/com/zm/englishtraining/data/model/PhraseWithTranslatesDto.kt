package com.zm.englishtraining.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.zm.englishtraining.domain.model.PhraseWithTranslates

data class PhraseWithTranslatesDto(
    @Embedded
    val phrase: PhraseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "phraseId"
    )
    val translates: List<TranslateEntity>
) {

    companion object {

        fun PhraseWithTranslatesDto.toDomain(): PhraseWithTranslates {
            return PhraseWithTranslates(
                phraseId = phrase.id,
                phrase = phrase.content,
                translates = translates.map { it.content }
            )
        }
    }
}