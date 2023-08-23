package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Category
import com.zm.englishtraining.domain.model.Phrase
import com.zm.englishtraining.domain.model.PhraseWithTranslates

interface Repository {

    suspend fun fetchCategories(): List<Category>

    suspend fun fetchPhrasesByCategoriesIds(ids: LongArray): List<PhraseWithTranslates>

    suspend fun insertData()

    suspend fun updateData()

    suspend fun fetchLevelByPhrase(phrase: String): Int

    suspend fun correctAnswer(phrase: String, level: Int)
}