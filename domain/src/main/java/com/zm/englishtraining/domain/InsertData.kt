package com.zm.englishtraining.domain

interface InsertData {

    suspend operator fun invoke()

    class Impl(
        private val repository: Repository
    ) : InsertData {

        override suspend fun invoke() {
            repository.insertData()
        }
    }
}