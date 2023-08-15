package com.zm.englishtraining.domain

import com.zm.englishtraining.domain.model.Topic

interface Repository {

    fun fetchTopics(): List<Topic>
}