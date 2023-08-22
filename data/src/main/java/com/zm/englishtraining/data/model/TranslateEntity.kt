package com.zm.englishtraining.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "translates", foreignKeys = [
    ForeignKey(
        entity = PhraseEntity::class,
        parentColumns = ["id"],
        childColumns = ["phraseId"]
    )
])
data class TranslateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val content: String,
    val phraseId: Long
)