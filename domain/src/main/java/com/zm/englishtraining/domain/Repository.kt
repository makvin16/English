package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Category

interface Repository {

    suspend fun fetchCategories(): List<Category>

    suspend fun insertCategory(category: Category)

}