package com.zm.englishtraining

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.data.CacheDataSource
import com.zm.englishtraining.data.CacheDataSourceImpl
import com.zm.englishtraining.data.FileDataSource
import com.zm.englishtraining.data.RepositoryImpl
import com.zm.englishtraining.db.AppDataBase
import com.zm.englishtraining.domain.CorrectAnswer
import com.zm.englishtraining.domain.GetCategories
import com.zm.englishtraining.domain.GetLevelByPhrase
import com.zm.englishtraining.domain.GetPhrasesByCategoriesIds
import com.zm.englishtraining.domain.InsertData
import com.zm.englishtraining.domain.Repository
import com.zm.englishtraining.domain.UpdateData
import com.zm.englishtraining.game.GameProvider
import com.zm.englishtraining.game.GameViewModel
import com.zm.englishtraining.start_ui.StartProvider
import com.zm.englishtraining.start_ui.StartViewModel

class App : Application(), StartProvider, GameProvider {

    private val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(this, AppDataBase::class.java, "english.db")
            .build()
    }

    private val _cacheDataSource: CacheDataSource by lazy {
        val pref = getSharedPreferences("EnglishTrainingPref", Context.MODE_PRIVATE)
        val fileDataSource = FileDataSource.Impl(assets)
        CacheDataSourceImpl(
            pref = pref,
            fileDataSource = fileDataSource,
            categoriesDao = appDataBase.categoriesDao(),
            phrasesDao = appDataBase.phrasesDao(),
            translatesDao = appDataBase.translatesDao(),
            phraseWithTranslatesDao = appDataBase.phraseWithTranslatesDao()
        )
    }

    private val _repository: Repository by lazy {
        RepositoryImpl(cacheDataSource = _cacheDataSource)
    }

    private val _factoryStart: StartViewModelFactory by lazy {
        val getCategories = GetCategories.Impl(repository = _repository)
        val insertData = InsertData.Impl(repository = _repository)
        val updateData = UpdateData.Impl(repository = _repository)
        StartViewModelFactory(
            getCategories = getCategories,
            insertData = insertData,
            updateData = updateData
        )
    }
    override val factoryStart: ViewModelAssistedFactory<StartViewModel>
        get() = _factoryStart

    override fun factoryGame(ids: LongArray, mode: ModeUi): ViewModelAssistedFactory<GameViewModel> {
        val getPhrasesByCategoriesIds = GetPhrasesByCategoriesIds.Impl(repository = _repository)
        val correctAnswer = CorrectAnswer.Impl(repository = _repository)
        val getLevelByPhrase = GetLevelByPhrase.Impl(repository = _repository)
        return GameViewModelFactory(
            ids = ids,
            mode = mode,
            getPhrasesByCategoriesIds = getPhrasesByCategoriesIds,
            getLevelByPhrase = getLevelByPhrase,
            correctAnswer = correctAnswer
        )
    }

    override fun onCreate() {
        super.onCreate()
    }

    class StartViewModelFactory(
        private val getCategories: GetCategories,
        private val insertData: InsertData,
        private val updateData: UpdateData
    ) : ViewModelAssistedFactory<StartViewModel> {

        override fun create(savedStateHandle: SavedStateHandle): StartViewModel {
            return StartViewModel(
                getCategories = getCategories,
                insertData = insertData,
                updateData = updateData
            )
        }
    }

    class GameViewModelFactory(
        private val ids: LongArray,
        private val mode: ModeUi,
        private val getPhrasesByCategoriesIds: GetPhrasesByCategoriesIds,
        private val getLevelByPhrase: GetLevelByPhrase,
        private val correctAnswer: CorrectAnswer
    ) : ViewModelAssistedFactory<GameViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): GameViewModel {
            return GameViewModel(
                ids = ids,
                mode = mode,
                getPhrasesByCategoriesIds = getPhrasesByCategoriesIds,
                getLevelByPhrase = getLevelByPhrase,
                correctAnswer = correctAnswer
            )
        }
    }
}