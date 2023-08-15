package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.TopicDto

interface CacheDataSource {

    fun fetchTopics(): List<TopicDto>
}