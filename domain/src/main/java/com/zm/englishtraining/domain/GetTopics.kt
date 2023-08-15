package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Topic

interface GetTopics {

    suspend operator fun invoke(): List<Topic>

    class Impl(
        private val repository: Repository
    ) : GetTopics {

        override suspend fun invoke(): List<Topic> {
            return repository.fetchTopics()
        }
    }
}