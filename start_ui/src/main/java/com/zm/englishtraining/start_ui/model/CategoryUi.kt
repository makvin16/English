package com.zm.englishtraining.start_ui.model

import com.zm.englishtraining.core.model.Ui
import com.zm.englishtraining.domain.model.Category

data class CategoryUi(
    val id: Long,
    val name: String,
    val isChoose: Boolean = false
) : Ui {

    companion object {

        fun Category.toUi(): CategoryUi = with(this) {
            return CategoryUi(
                id = id,
                name = name
            )
        }
    }
}