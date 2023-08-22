package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Category

interface GetCategories {

    suspend operator fun invoke(): List<Category>

    class Impl(
        private val repository: Repository
    ) : GetCategories {

        override suspend fun invoke(): List<Category> {
            return repository.fetchCategories()
        }
    }
}