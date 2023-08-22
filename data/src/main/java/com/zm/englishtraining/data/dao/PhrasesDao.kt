package com.zm.englishtraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zm.englishtraining.data.model.PhraseEntity

@Dao
interface PhrasesDao {

    @Insert
    fun insertPhrase(phrase: PhraseEntity): Long

    @Query("DELETE FROM phrases")
    fun deleteAllPhrases()
}
