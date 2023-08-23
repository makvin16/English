package com.zm.englishtraining.core.model

sealed class Level {
    abstract val value: Int

    object Zero : Level() {
        override val value: Int get() = 0
    }

    object One : Level() {
        override val value: Int get() = 1
    }

    object Two : Level() {
        override val value: Int get() = 2
    }

    object Three : Level() {
        override val value: Int get() = 3
    }

    object Four : Level() {
        override val value: Int get() = 4
    }

    object Five : Level() {
        override val value: Int get() = 5
    }

    fun levelUp(): Level {
        val newLevel = this.value + 1
        return if (newLevel > Five.value) Five else build(newLevel)
    }

    companion object {

        fun build(level: Int): Level {
            return when(level) {
                1 -> One
                2 -> Two
                3 -> Three
                4 -> Four
                5 -> Five
                else -> Zero
            }
        }
    }
}