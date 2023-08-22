package com.zm.englishtraining

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.data.CacheDataSource
import com.zm.englishtraining.data.CacheDataSourceImpl
import com.zm.englishtraining.data.FileDataSource
import com.zm.englishtraining.data.RepositoryImpl
import com.zm.englishtraining.db.AppDataBase
import com.zm.englishtraining.domain.GetCategories
import com.zm.englishtraining.domain.InsertData
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.start_ui.StartProvider
import com.zm.englishtraining.start_ui.StartViewModel

class App : Application(), StartProvider {

    private val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(this, AppDataBase::class.java, "english.db")
            .build()
    }

    private val _cacheDataSource: CacheDataSource by lazy {
        val fileDataSource = FileDataSource.Impl(assets)
        CacheDataSourceImpl(
            fileDataSource = fileDataSource,
            categoriesDao = appDataBase.categoriesDao(),
            phrasesDao = appDataBase.phrasesDao(),
            translatesDao = appDataBase.translatesDao()
        )
    }

    private val _repository: Repository by lazy {
        RepositoryImpl(cacheDataSource = _cacheDataSource)
    }

    private val _factoryStart: StartViewModelFactory by lazy {
        val getCategories = GetCategories.Impl(repository = _repository)
        val insertData = InsertData.Impl(repository = _repository)
        StartViewModelFactory(getCategories = getCategories, insertData = insertData)
    }
    override val factoryStart: ViewModelAssistedFactory<StartViewModel>
        get() = _factoryStart

    override fun onCreate() {
        super.onCreate()
    }

    class StartViewModelFactory(
        private val getCategories: GetCategories,
        private val insertData: InsertData
    ) : ViewModelAssistedFactory<StartViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): StartViewModel {
            return StartViewModel(
                getCategories = getCategories,
                insertData = insertData
            )
        }
    }
}