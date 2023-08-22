package com.zm.englishtraining.start_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zm.englishtraining.core.base.BaseViewModel
import com.zm.englishtraining.core.model.EmptyUi
import com.zm.englishtraining.domain.GetCategories
import com.zm.englishtraining.domain.InsertData
import com.zm.englishtraining.domain.model.Category
import com.zm.englishtraining.start_ui.model.CategoryUi
import com.zm.englishtraining.start_ui.model.CategoryUi.Companion.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(
    private val getCategories: GetCategories,
    private val insertData: InsertData
) : BaseViewModel() {

    private val _categories = MutableLiveData<List<CategoryUi>>()
    val categories: LiveData<List<CategoryUi>> = _categories

    init {
        showProgress.value = EmptyUi
        viewModelScope.launch(Dispatchers.IO) {
            var categories = getCategories().map { it.toUi() }
            insertData()
            if (categories.isEmpty()) {
                insertData()
            }
            categories = getCategories().map { it.toUi() }
            _categories.postValue(categories)
            hideProgress.postValue(EmptyUi)
        }
    }

    fun onClickChoiceAll() {
        val newContent = categories.value?.toMutableList()
            ?.map { it.copy(isChoose = true) }
            ?: return
        _categories.postValue(newContent)
    }

    fun onClickTopic(topic: CategoryUi) {
        val newContent = categories.value?.toMutableList() ?: return
        val index = newContent.indexOf(topic)
        newContent.removeAt(index)
        newContent.add(index, topic.copy(isChoose = topic.isChoose.not()))
        _categories.postValue(newContent)
    }
}