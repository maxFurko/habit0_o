package com.LNUproject.habitt0_o.managers

import android.content.Context
import com.LNUproject.habitt0_o.database.AppDatabase
import com.LNUproject.habitt0_o.database.repositories.HabitRepository
import com.LNUproject.habitt0_o.database.repositories.TaskLogRepository

class DatabaseManager(context : Context) {
    private val db = AppDatabase.getDatabase(context)
    val habitRepository = HabitRepository(db.habitDao())
    val taskLogRepository = TaskLogRepository(db.taskLogDao())
}