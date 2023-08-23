package com.zm.englishtraining.domain

interface CorrectAnswer {

    suspend operator fun invoke(phrase: String, level: Int)

    class Impl(
        private val repository: Repository
    ) : CorrectAnswer {

        override suspend fun invoke(phrase: String, level: Int) {
            repository.correctAnswer(phrase, level)
        }
    }
}