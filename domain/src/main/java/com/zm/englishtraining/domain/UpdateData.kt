package com.zm.englishtraining.domain

interface UpdateData {

    suspend operator fun invoke()

    class Impl(
        private val repository: Repository
    ) : UpdateData {

        override suspend fun invoke() {
            repository.updateData()
        }
    }
}