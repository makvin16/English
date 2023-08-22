package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Category

interface InsertCategory {

    suspend operator fun invoke(category: Category)

    class Impl(
        private val repository: Repository
    ) : InsertCategory {

        override suspend fun invoke(category: Category) {
            repository.insertCategory(category)
        }
    }
}