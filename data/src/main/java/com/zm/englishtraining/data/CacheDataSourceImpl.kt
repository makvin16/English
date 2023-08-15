package com.zm.englishtraining.data

import com.zm.englishtraining.data.model.TopicDto

class CacheDataSourceImpl : CacheDataSource {

    override fun fetchTopics(): List<TopicDto> {
        return listOf(
            TopicDto(1, "name1"),
            TopicDto(2, "name2"),
            TopicDto(3, "name3"),
            TopicDto(4, "name4"),
            TopicDto(5, "name5"),
        )
    }
}