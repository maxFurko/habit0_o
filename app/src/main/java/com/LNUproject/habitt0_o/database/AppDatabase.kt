package com.LNUproject.habitt0_o.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.LNUproject.habitt0_o.database.dao.HabitDao
import com.LNUproject.habitt0_o.database.dao.TaskLogDao
import com.LNUproject.habitt0_o.models.Habit
import com.LNUproject.habitt0_o.models.TaskLog

@Database(entities = [Habit::class, TaskLog::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao
    abstract fun taskLogDao() : TaskLogDao

    companion object {
        private const val DATABASE_NAME = "database_app"
        const val DATABASE_LOG_TAG = "database_log"

        @Volatile
        private var DATABASE_INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return DATABASE_INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME)
                        .build()

                DATABASE_INSTANCE = instance

                return@synchronized instance
            }
        }
    }
}