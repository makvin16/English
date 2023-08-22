package com.zm.englishtraining

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.data.CacheDataSource
import com.zm.englishtraining.data.CacheDataSourceImpl
import com.zm.englishtraining.data.RepositoryImpl
import com.zm.englishtraining.db.AppDataBase
import com.zm.englishtraining.domain.GetCategories
import com.zm.englishtraining.domain.InsertCategory
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.start_ui.StartProvider
import com.zm.englishtraining.start_ui.StartViewModel

class App : Application(), StartProvider {

    private val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(this, AppDataBase::class.java, "english.db")
            .build()
    }

    private val _cacheDataSource: CacheDataSource by lazy {
        val pref = getSharedPreferences("EnglishTrainingPref", Context.MODE_PRIVATE)
        CacheDataSourceImpl(sharedPreferences = pref, categoriesDao = appDataBase.phrasesDao())
    }

    private val _repository: Repository by lazy {
        RepositoryImpl(cacheDataSource = _cacheDataSource)
    }

    private val _factoryStart: StartViewModelFactory by lazy {
        val getCategories = GetCategories.Impl(repository = _repository)
        val insertCategories = InsertCategory.Impl(repository = _repository)
        StartViewModelFactory(getCategories = getCategories, insertCategories = insertCategories)
    }
    override val factoryStart: ViewModelAssistedFactory<StartViewModel>
        get() = _factoryStart

    override fun onCreate() {
        super.onCreate()
    }

    class StartViewModelFactory(
        private val getCategories: GetCategories,
        private val insertCategories: InsertCategory
    ) : ViewModelAssistedFactory<StartViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): StartViewModel {
            return StartViewModel(
                getCategories = getCategories,
                insertCategories = insertCategories
            )
        }
    }
}