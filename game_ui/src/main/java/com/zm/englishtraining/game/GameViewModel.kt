package com.zm.englishtraining.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zm.englishtraining.core.base.BaseViewModel
import com.zm.englishtraining.core.ext.log
import com.zm.englishtraining.core.model.EmptyUi
import com.zm.englishtraining.core.model.Level
import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.core.viewmodel.SingleLiveData
import com.zm.englishtraining.domain.CorrectAnswer
import com.zm.englishtraining.domain.GetLevelByPhrase
import com.zm.englishtraining.domain.GetPhrasesByCategoriesIds
import com.zm.englishtraining.domain.model.PhraseWithTranslates
import com.zm.englishtraining.game.model.PhraseUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(
    private val ids: LongArray,
    private val mode: ModeUi,
    private val getPhrasesByCategoriesIds: GetPhrasesByCategoriesIds,
    private val getLevelByPhrase: GetLevelByPhrase,
    private val correctAnswer: CorrectAnswer
) : BaseViewModel() {

    private val _phrases = MutableLiveData<List<PhraseUi>>()
    val phrases: LiveData<List<PhraseUi>> = _phrases

    private val _scroll = SingleLiveData<Int>()
    val scroll: LiveData<Int> = _scroll

    private var currentPosition = 0

    init {
        showProgress.value = EmptyUi
        viewModelScope.launch(Dispatchers.IO) {
            val phrases = getPhrasesByCategoriesIds(ids).map {
                val question = when (mode) {
                    ModeUi.EnglishToRussian -> it.phrase
                    ModeUi.RussianToEnglish -> it.translates.first()
                }
                val level = getLevelByPhrase(question)
                mapPhrase(it, level)
            }
            log("$phrases")
            _phrases.postValue(phrases)
            hideProgress.postValue(EmptyUi)
        }
    }

    private fun mapPhrase(phrase: PhraseWithTranslates, level: Int): PhraseUi = with(phrase) {
        val (question, answers) = when (mode) {
            ModeUi.EnglishToRussian -> this.phrase to translates
            ModeUi.RussianToEnglish -> translates.first() to listOf(this.phrase)
        }
        return PhraseUi(
            id = phraseId,
            question = question,
            answers = answers,
            isAnswerVisible = false,
            level = Level.build(level)
        )
    }

    fun onClickHelp(phrase: PhraseUi) {
        val newPhrases = phrases.value?.toMutableList() ?: return
        newPhrases.removeAt(currentPosition)
        newPhrases.add(currentPosition, phrase.copy(isAnswerVisible = true))
        _phrases.postValue(newPhrases)
    }

    fun onClickConfirm(answer: String, result: (Boolean, String) -> Unit) {
        val currentPhrase = phrases.value?.get(currentPosition) ?: return
        log("old: $answer")
        val answerFilter = answer.lowercase().trim().filter {
            it.isLetterOrDigit() || it.isWhitespace()
        }
        var isCorrect = false
        currentPhrase.answers.forEach { a ->
            if (isCorrect) return@forEach
            val correctAnswerFilter = a.lowercase().trim().filter {
                it.isLetterOrDigit() || it.isWhitespace()
            }
            isCorrect = correctAnswerFilter == answerFilter
            log("correct: $correctAnswerFilter $isCorrect")
        }
        log("new: $answerFilter")
        result.invoke(isCorrect, currentPhrase.answers.toString())
        if (isCorrect && currentPhrase.isAnswerVisible.not()) {
            viewModelScope.launch(Dispatchers.IO) {
                correctAnswer(currentPhrase.question, currentPhrase.level.levelUp().value)
            }
        }
    }

    fun onNextQuestion() {
        val newPhrases = phrases.value?.toMutableList() ?: return
        val newPhrase = newPhrases[currentPosition]
        newPhrases.removeAt(currentPosition)
        newPhrases.add(currentPosition, newPhrase.copy(isAnswerVisible = false))
        _phrases.postValue(newPhrases)
        currentPosition = if (currentPosition == newPhrases.size - 1) 0 else currentPosition + 1
        _scroll.postValue(currentPosition)
    }
}