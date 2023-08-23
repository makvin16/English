package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.CategoryEntity.Companion.toDomain
import com.zm.englishtraining.data.model.PhraseWithTranslatesDto.Companion.toDomain
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.domain.model.Category
import com.zm.englishtraining.domain.model.PhraseWithTranslates

class RepositoryImpl(
    private val cacheDataSource: CacheDataSource
) : Repository {

    override suspend fun fetchCategories(): List<Category> {
        return cacheDataSource.fetchCategories().map { it.toDomain() }
    }

    override suspend fun fetchPhrasesByCategoriesIds(ids: LongArray): List<PhraseWithTranslates> {
        return cacheDataSource.fetchPhrasesByCategoriesIds(ids).map { it.toDomain() }
    }

    override suspend fun insertData() {
        cacheDataSource.insertData()
    }

    override suspend fun updateData() {
        cacheDataSource.updateData()
    }

    override suspend fun fetchLevelByPhrase(phrase: String): Int {
        return cacheDataSource.fetchLevelByPhrase(phrase)
    }

    override suspend fun correctAnswer(phrase: String, level: Int) {
        cacheDataSource.correctAnswer(phrase, level)
    }
}