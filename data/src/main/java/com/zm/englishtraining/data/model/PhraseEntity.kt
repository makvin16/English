package com.zm.englishtraining.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "phrases", foreignKeys = [
    ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"]
    )
])
data class PhraseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val content: String,
    val categoryId: Long
)