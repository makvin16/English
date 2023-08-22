package com.zm.englishtraining.start_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zm.englishtraining.domain.GetCategories
import com.zm.englishtraining.domain.InsertCategory
import com.zm.englishtraining.domain.model.Category
import com.zm.englishtraining.start_ui.model.CategoryUi
import com.zm.englishtraining.start_ui.model.CategoryUi.Companion.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(
    private val getCategories: GetCategories,
    private val insertCategories: InsertCategory
) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryUi>>()
    val categories: LiveData<List<CategoryUi>> = _categories

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var categories = getCategories().map { it.toUi() }
            if (categories.isEmpty()) {
                insertCategories(Category(1, "Present Simple"))
            }
            categories = getCategories().map { it.toUi() }
            _categories.postValue(categories)
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