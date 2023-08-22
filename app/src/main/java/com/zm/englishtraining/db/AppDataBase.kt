package com.zm.englishtraining.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zm.englishtraining.data.CategoriesDao
import com.zm.englishtraining.data.model.CategoryEntity
import com.zm.englishtraining.data.model.LanguageEntity

@Database(
    version = 1,
    entities = [CategoryEntity::class, LanguageEntity.English::class, LanguageEntity.Russian::class]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun phrasesDao(): CategoriesDao
}