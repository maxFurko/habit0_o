package com.LNUproject.habitt0_o.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.LNUproject.habitt0_o.managers.DatabaseManager
import com.LNUproject.habitt0_o.managers.IconManager
import com.LNUproject.habitt0_o.managers.TaskManager
import com.LNUproject.habitt0_o.models.HabitWithTaskLogs
import com.LNUproject.habitt0_o.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {
    private var hasHabits = false
    private val databaseManager = DatabaseManager(application)
    private val taskManager = TaskManager(databaseManager)
    val iconManager = IconManager(application)

    fun createTaskLog(taskModel: TaskModel, status : String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskManager.insertTaskLog(taskModel, status, System.currentTimeMillis())
        }
    }

    fun generateDailyTasks(habits : List<HabitWithTaskLogs>) {
        hasHabits = habits.isNotEmpty()

        viewModelScope.launch(Dispatchers.Main) {
            taskManager.generateDailyTasks(habits)
        }
    }

    fun hasHabits() : Boolean {
        return hasHabits
    }

    fun getHabitsWithTaskLogs() : LiveData<List<HabitWithTaskLogs>> {
        return databaseManager.habitRepository.habitsWithTaskLogs
    }

    fun getTasks() : LiveData<ArrayList<TaskModel>> {
        return taskManager.tasks
    }
}