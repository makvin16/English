package com.zm.englishtraining

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.data.CacheDataSource
import com.zm.englishtraining.data.CacheDataSourceImpl
import com.zm.englishtraining.data.RepositoryImpl
import com.zm.englishtraining.domain.GetTopics
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.start_ui.StartProvider
import com.zm.englishtraining.start_ui.StartViewModel

class App : Application(), StartProvider {

    private val _cacheDataSource: CacheDataSource by lazy {
        CacheDataSourceImpl()
    }

    private val _repository: Repository by lazy {
        RepositoryImpl(cacheDataSource = _cacheDataSource)
    }

    private val _factoryStart: StartViewModelFactory by lazy {
        val getTopics = GetTopics.Impl(repository = _repository)
        StartViewModelFactory(getTopics = getTopics)
    }
    override val factoryStart: ViewModelAssistedFactory<StartViewModel>
        get() = _factoryStart

    override fun onCreate() {
        super.onCreate()

    }

    class StartViewModelFactory(
        private val getTopics: GetTopics
    ) : ViewModelAssistedFactory<StartViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): StartViewModel {
            return StartViewModel(getTopics = getTopics)
        }
    }
}