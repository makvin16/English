package com.zm.englishtraining.data.model

import com.zm.englishtraining.domain.model.Topic

data class TopicDto(
    val id: Int?,
    val name: String?
) {

    companion object {

        fun TopicDto.toDomain(): Topic {
            return Topic(
                id = this.id ?: 0,
                name = this.name ?: ""
            )
        }
    }
}