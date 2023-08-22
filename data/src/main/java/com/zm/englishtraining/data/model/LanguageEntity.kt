package com.zm.englishtraining.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

abstract class LanguageEntity {
    abstract val id: Long
    abstract val content: String
    abstract val categoryId: Long

    @Entity(tableName = "english", indices = [Index("id")], foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        )
    ])
    data class English(
        @PrimaryKey(autoGenerate = true) override val id: Long,
        override val content: String,
        override val categoryId: Long
    ) : LanguageEntity()

    @Entity(tableName = "russian", indices = [Index("id")], foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        )
    ])
    data class Russian(
        @PrimaryKey(autoGenerate = true) override val id: Long,
        override val content: String,
        override val categoryId: Long
    ) : LanguageEntity()
}