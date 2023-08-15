package com.zm.englishtraining.start_ui.model

import com.zm.englishtraining.core.model.Ui
import com.zm.englishtraining.domain.model.Topic

data class TopicUi(
    val id: Int,
    val name: String,
    val isChoose: Boolean = false
) : Ui {

    companion object {

        fun Topic.toUi(): TopicUi = with(this) {
            return TopicUi(
                id = id,
                name = name
            )
        }
    }
}