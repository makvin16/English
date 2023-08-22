package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.CategoryEntity.Companion.toDomain
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.domain.model.Category

class RepositoryImpl(
    private val cacheDataSource: CacheDataSource
) : Repository {

    override suspend fun fetchCategories(): List<Category> {
        return cacheDataSource.fetchCategories().map { it.toDomain() }
    }

    override suspend fun insertData() {
        cacheDataSource.insertData()
    }
}