package com.zm.englishtraining.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zm.englishtraining.data.model.CategoryEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): List<CategoryEntity>

    @Insert(entity = CategoryEntity::class)
    fun insertCategory(category: CategoryEntity)

    @Delete(entity = CategoryEntity::class)
    fun delete()
}