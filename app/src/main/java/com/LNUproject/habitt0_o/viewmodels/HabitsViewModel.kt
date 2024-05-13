package com.LNUproject.habitt0_o.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.LNUproject.habitt0_o.managers.DatabaseManager
import com.LNUproject.habitt0_o.managers.IconManager
import com.LNUproject.habitt0_o.models.Habit

class HabitsViewModel(application: Application) : AndroidViewModel(application) {

    val iconManager = IconManager(application)
    private val databaseManager : DatabaseManager = DatabaseManager(getApplication())

    fun getHabits() : LiveData<List<Habit>> {
        return databaseManager.habitRepository.habits
    }
}