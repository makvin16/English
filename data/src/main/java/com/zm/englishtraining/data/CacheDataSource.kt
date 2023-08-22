package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.CategoryEntity

interface CacheDataSource {

    suspend fun fetchCategories(): List<CategoryEntity>

    suspend fun insertCategory(category: CategoryEntity)

    suspend fun deleteAllCategories()
}