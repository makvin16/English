package com.zm.englishtraining.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zm.englishtraining.data.dao.CategoriesDao
import com.zm.englishtraining.data.dao.PhraseWithTranslatesDao
import com.zm.englishtraining.data.dao.PhrasesDao
import com.zm.englishtraining.data.dao.TranslatesDao
import com.zm.englishtraining.data.model.CategoryEntity
import com.zm.englishtraining.data.model.PhraseEntity
import com.zm.englishtraining.data.model.TranslateEntity

@Database(
    version = 1,
    entities = [CategoryEntity::class, PhraseEntity::class, TranslateEntity::class]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun phrasesDao(): PhrasesDao
    abstract fun translatesDao(): TranslatesDao
    abstract fun phraseWithTranslatesDao(): PhraseWithTranslatesDao
}