package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.CategoryEntity

interface CacheDataSource {

    suspend fun fetchCategories(): List<CategoryEntity>

    suspend fun insertData()

    suspend fun deleteAllCategories()
}