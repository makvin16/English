package com.zm.englishtraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zm.englishtraining.data.model.TranslateEntity

@Dao
interface TranslatesDao {

    @Insert
    fun insertTranslates(translates: List<TranslateEntity>)

    @Query("DELETE FROM translates")
    fun deleteAllTranslates()
}