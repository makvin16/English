package com.zm.englishtraining.data

import android.content.SharedPreferences
import com.zm.englishtraining.data.model.CategoryEntity

class CacheDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
    private val categoriesDao: CategoriesDao
) : CacheDataSource {

    override suspend fun fetchCategories(): List<CategoryEntity> {
        return categoriesDao.getCategories()
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoriesDao.insertCategory(category)
    }

    override suspend fun deleteAllCategories() {
        categoriesDao.delete()
    }


}