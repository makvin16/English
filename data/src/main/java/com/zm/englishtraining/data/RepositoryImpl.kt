package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.TopicDto.Companion.toDomain
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.domain.model.Topic

class RepositoryImpl(
    private val cacheDataSource: CacheDataSource
) : Repository {

    override fun fetchTopics(): List<Topic> {
        return cacheDataSource.fetchTopics().map { it.toDomain() }
    }
}