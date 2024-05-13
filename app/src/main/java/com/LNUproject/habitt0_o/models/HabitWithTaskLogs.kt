package com.LNUproject.habitt0_o.models

import androidx.room.Embedded
import androidx.room.Relation
import com.LNUproject.habitt0_o.utils.ScoreUtil

data class HabitWithTaskLogs (
    @Embedded
    val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val taskLogs: List<TaskLog>
) {
    init {
        val shouldResetHabitScore = ScoreUtil.shouldResetHabitScore(this)
        if(shouldResetHabitScore) habit.score = 0
    }
}