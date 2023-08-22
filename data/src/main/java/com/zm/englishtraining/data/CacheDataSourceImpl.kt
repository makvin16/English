package com.zm.englishtraining.data

import com.zm.englishtraining.core.ext.log
import com.zm.englishtraining.data.dao.CategoriesDao
import com.zm.englishtraining.data.dao.PhrasesDao
import com.zm.englishtraining.data.dao.TranslatesDao
import com.zm.englishtraining.data.model.CategoryEntity
import com.zm.englishtraining.data.model.Data.Companion.toCategoryEntity
import com.zm.englishtraining.data.model.Data.Companion.toPhraseEntity
import com.zm.englishtraining.data.model.Data.Companion.toTranslatesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CacheDataSourceImpl(
    private val fileDataSource: FileDataSource,
    private val categoriesDao: CategoriesDao,
    private val phrasesDao: PhrasesDao,
    private val translatesDao: TranslatesDao
) : CacheDataSource {

    override suspend fun fetchCategories(): List<CategoryEntity> {
        return categoriesDao.getCategories()
    }

    override suspend fun insertData(): Unit = withContext(Dispatchers.IO) {
        try {
            categoriesDao.deleteAllCategories()
            phrasesDao.deleteAllPhrases()
            translatesDao.deleteAllTranslates()
            val listData = fileDataSource.read()
            listData.forEach { data ->
                val categoryId = categoriesDao.insertCategory(data.toCategoryEntity())
                log(categoryId.toString())
                data.phrases.forEach { phrase ->
                    val phraseId = phrasesDao.insertPhrase(phrase.toPhraseEntity(categoryId))
                    translatesDao.insertTranslates(phrase.toTranslatesEntity(phraseId))
                }
                log("$data")
            }
        } catch (e: Exception) {
            log(e.message.toString())
        }
    }

    override suspend fun deleteAllCategories() {

    }
}