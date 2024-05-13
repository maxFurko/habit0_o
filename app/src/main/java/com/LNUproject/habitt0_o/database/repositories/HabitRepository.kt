package com.LNUproject.habitt0_o.database.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.LNUproject.habitt0_o.database.AppDatabase
import com.LNUproject.habitt0_o.database.dao.HabitDao
import com.LNUproject.habitt0_o.models.Habit
import com.LNUproject.habitt0_o.models.HabitWithTaskLogs

@Suppress("RedundantSuspendModifier")

class HabitRepository(private val habitDao: HabitDao) {
    val habits = habitDao.getAll()
    val habitsWithTaskLogs = habitDao.getHabitsWithTaskLogs()

    @WorkerThread
    suspend fun createHabit(habit : Habit) : Long {
        val id = habitDao.create(habit)
        Log.d(AppDatabase.DATABASE_LOG_TAG, "Habit inserted to database with id $id")

        return id
    }

    @WorkerThread
    suspend fun deleteHabit(habit : Habit) : Int {
        val rows = habitDao.delete(habit)

        Log.d(AppDatabase.DATABASE_LOG_TAG, "$rows rows deleted from the database")

        return rows
    }

    @WorkerThread
    suspend fun updateHabit(habit : Habit) : Int {
        val rows = habitDao.update(habit)

        Log.d(AppDatabase.DATABASE_LOG_TAG, "$rows rows updated in the database")

        return rows
    }

    @WorkerThread
    suspend fun getHabitById(id : Long) : Habit? {
        return habitDao.getById(id)
    }

    fun getHabitWithTaskLogsById(id : Long) : LiveData<HabitWithTaskLogs?> {
        return habitDao.getHabitWithTaskLogsById(id)
    }

    @WorkerThread
    suspend fun deleteAll() : Int {
        val rows = habitDao.deleteAll()

        Log.d(AppDatabase.DATABASE_LOG_TAG, "$rows rows deleted from the database")

        return rows
    }
}