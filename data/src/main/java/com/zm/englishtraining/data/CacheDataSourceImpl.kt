package com.zm.englishtraining.data

import android.content.SharedPreferences
import com.zm.englishtraining.core.ext.log
import com.zm.englishtraining.data.dao.CategoriesDao
import com.zm.englishtraining.data.dao.PhraseWithTranslatesDao
import com.zm.englishtraining.data.dao.PhrasesDao
import com.zm.englishtraining.data.dao.TranslatesDao
import com.zm.englishtraining.data.model.CategoryEntity
import com.zm.englishtraining.data.model.Data.Companion.toCategoryEntity
import com.zm.englishtraining.data.model.Data.Companion.toPhraseEntity
import com.zm.englishtraining.data.model.Data.Companion.toTranslatesEntity
import com.zm.englishtraining.data.model.PhraseWithTranslatesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CacheDataSourceImpl(
    private val pref: SharedPreferences,
    private val fileDataSource: FileDataSource,
    private val categoriesDao: CategoriesDao,
    private val phrasesDao: PhrasesDao,
    private val translatesDao: TranslatesDao,
    private val phraseWithTranslatesDao: PhraseWithTranslatesDao
) : CacheDataSource {

    override suspend fun fetchCategories(): List<CategoryEntity> {
        return categoriesDao.getCategories()
    }

    override suspend fun fetchPhrasesByCategoriesIds(ids: LongArray): List<PhraseWithTranslatesDto> {
        return phraseWithTranslatesDao.fetchPhrasesWithTranslates(ids)
    }

    override suspend fun insertData(): Unit = withContext(Dispatchers.IO) {
        try {
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

    override suspend fun updateData() {
        translatesDao.deleteAllTranslates()
        phrasesDao.deleteAllPhrases()
        categoriesDao.deleteAllCategories()
        insertData()
    }

    override suspend fun fetchLevelByPhrase(phrase: String): Int {
        log("GET: $phrase")
        return pref.getInt(phrase, 0)
    }

    override suspend fun correctAnswer(phrase: String, level: Int) {
        log("SET: $phrase")
        pref.edit().putInt(phrase, level).apply()
    }
}