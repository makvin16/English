package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.CategoryEntity
import com.zm.englishtraining.data.model.PhraseWithTranslatesDto

interface CacheDataSource {

    suspend fun fetchCategories(): List<CategoryEntity>

    suspend fun fetchPhrasesByCategoriesIds(ids: LongArray): List<PhraseWithTranslatesDto>

    suspend fun insertData()

    suspend fun updateData()

    suspend fun fetchLevelByPhrase(phrase: String): Int

    suspend fun correctAnswer(phrase: String, level: Int)
}