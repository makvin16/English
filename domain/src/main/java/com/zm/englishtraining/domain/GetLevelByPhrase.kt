package com.zm.englishtraining.domain

interface GetLevelByPhrase {

    suspend operator fun invoke(phrase: String): Int

    class Impl(
        private val repository: Repository
    ) : GetLevelByPhrase {

        override suspend operator fun invoke(phrase: String): Int {
            return repository.fetchLevelByPhrase(phrase)
        }
    }
}