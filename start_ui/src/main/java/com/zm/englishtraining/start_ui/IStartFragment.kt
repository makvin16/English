package com.zm.englishtraining.start_ui

import com.zm.englishtraining.start_ui.model.CategoryUi

interface IStartFragment {

    fun onUpdateCategories(categories: List<CategoryUi>)
}