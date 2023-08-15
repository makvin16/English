package com.zm.englishtraining.start_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zm.englishtraining.domain.GetTopics
import com.zm.englishtraining.start_ui.model.TopicUi
import com.zm.englishtraining.start_ui.model.TopicUi.Companion.toUi
import kotlinx.coroutines.launch

class StartViewModel(
    private val getTopics: GetTopics
) : ViewModel() {

    private val _topics = MutableLiveData<List<TopicUi>>()
    val topics: LiveData<List<TopicUi>> = _topics

    init {
        viewModelScope.launch {
            val topics = getTopics().map { it.toUi() }
            _topics.postValue(topics)
        }
    }

    fun onClickChoiceAll() {
        val newContent = topics.value?.toMutableList()
            ?.map { it.copy(isChoose = true) }
            ?: return
        _topics.postValue(newContent)
    }

    fun onClickTopic(topic: TopicUi) {
        val newContent = topics.value?.toMutableList() ?: return
        val index = newContent.indexOf(topic)
        newContent.removeAt(index)
        newContent.add(index, topic.copy(isChoose = topic.isChoose.not()))
        _topics.postValue(newContent)
    }
}