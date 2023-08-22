package com.zm.englishtraining.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.zm.englishtraining.domain.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
) {

    companion object {

        fun CategoryEntity.toDomain(): Category {
            return Category(
                id = id,
                name = name
            )
        }

        fun Category.toDto(): CategoryEntity {
            return CategoryEntity(
                id = id,
                name = name
            )
        }
    }
}