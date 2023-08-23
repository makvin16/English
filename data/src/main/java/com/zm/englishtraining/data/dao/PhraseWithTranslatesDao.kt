package com.zm.englishtraining.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.zm.englishtraining.data.model.PhraseWithTranslatesDto

@Dao
interface PhraseWithTranslatesDao {

    @Transaction
    @Query("SELECT * FROM phrases WHERE categoryId IN (:ids)")
    fun fetchPhrasesWithTranslates(ids: LongArray): List<PhraseWithTranslatesDto>
}