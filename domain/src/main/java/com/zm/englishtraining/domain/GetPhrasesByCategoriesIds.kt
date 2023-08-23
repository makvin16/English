package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.PhraseWithTranslates

interface GetPhrasesByCategoriesIds {

    suspend operator fun invoke(ids: LongArray): List<PhraseWithTranslates>

    class Impl(
        private val repository: Repository
    ) : GetPhrasesByCategoriesIds {

        override suspend fun invoke(ids: LongArray): List<PhraseWithTranslates> {
            return repository.fetchPhrasesByCategoriesIds(ids).filter {
                it.phrase.isNotEmpty() && it.translates.isNotEmpty()
            }
        }
    }
}