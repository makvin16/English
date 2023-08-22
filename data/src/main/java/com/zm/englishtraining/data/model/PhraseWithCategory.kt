//package com.zm.englishtraining.data.model
//
//import androidx.room.Embedded
//import androidx.room.Relation
//
//data class PhraseWithCategory(
//    @Embedded
//    val phrase: PhraseEntity,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "categoryId"
//    )
//    val category: CategoryEntity
//)