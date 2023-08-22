package com.zm.englishtraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zm.englishtraining.data.model.CategoryEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): List<CategoryEntity>

    @Insert(entity = CategoryEntity::class)
    fun insertCategory(category: CategoryEntity): Long

    @Query("DELETE FROM categories")
    fun deleteAllCategories()
}