package com.LNUproject.habitt0_o.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.LNUproject.habitt0_o.managers.DatabaseManager
import com.LNUproject.habitt0_o.managers.IconManager
import com.LNUproject.habitt0_o.managers.TaskManager
import com.LNUproject.habitt0_o.models.HabitWithTaskLogs
import com.LNUproject.habitt0_o.models.TaskModel
import com.LNUproject.habitt0_o.utils.TaskUtil
import kotlinx.coroutines.launch

class TaskManagementViewModel(application: Application) : AndroidViewModel(application) {
    private var initialized = false

    private val databaseManager = DatabaseManager(getApplication())
    private val taskManager = TaskManager(databaseManager)
    val iconManager = IconManager(application)

    private lateinit var habitWithTaskLogs : LiveData<HabitWithTaskLogs?>

    private var selectedDateTimestamp : MutableLiveData<Long> = MutableLiveData()

    fun initialize(id : Long) {
        if (initialized) return

        selectedDateTimestamp.value = System.currentTimeMillis()
        habitWithTaskLogs = databaseManager.habitRepository.getHabitWithTaskLogsById(id)

        initialized = true
    }

    fun getHabitWithTaskLogsLiveData() : LiveData<HabitWithTaskLogs?> {
        return habitWithTaskLogs
    }

    fun createTaskLog(status : String) : Boolean {
        val habit = habitWithTaskLogs.value?: return false
        if(!canAddTaskLog()) return false

        viewModelScope.launch {
            taskManager.insertTaskLog(TaskModel(habit), status, getSelectedDateTimestamp())
        }

        return true
    }

    fun getSelectedDateTimestampLiveData() : LiveData<Long> {
        return selectedDateTimestamp
    }

    fun getSelectedDateTimestamp() : Long {
        return selectedDateTimestamp.value?: System.currentTimeMillis()
    }

    fun setSelectedDateTimestamp(timestamp: Long) {
        selectedDateTimestamp.value = timestamp
    }

    fun canAddTaskLog() : Boolean {
        val habitWithTaskLogs = habitWithTaskLogs.value?: return false
        return !TaskUtil.hasTaskLogForDate(habitWithTaskLogs, getSelectedDateTimestamp())
    }
}