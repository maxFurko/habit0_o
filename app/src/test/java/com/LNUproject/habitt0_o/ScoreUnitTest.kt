package com.LNUproject.habitt0_o

import com.LNUproject.habitt0_o.models.Habit
import com.LNUproject.habitt0_o.models.HabitWithTaskLogs
import com.LNUproject.habitt0_o.models.TaskLog
import com.LNUproject.habitt0_o.utils.CalendarUtil
import com.LNUproject.habitt0_o.utils.ScoreUtil
import org.joda.time.DateTime
import org.joda.time.DateTimeUtils
import org.junit.Assert
import org.junit.Test

class ScoreUnitTest {
    @Test
    fun resetHabitScore() {

        val habit = Habit()

        val taskLogs = ArrayList<TaskLog>()

        DateTimeUtils.setCurrentMillisFixed(1610193600000L) //Saturday
        taskLogs.add(createTaskLogTest(1609939668000L)) //Wednesday
        taskLogs.add(createTaskLogTest(1609766868000L)) //Monday

        habit.taskRecurrence = CalendarUtil.RRULE_EVERY_DAY

        val habitWithTaskLogs = HabitWithTaskLogs(habit, taskLogs)
        Assert.assertTrue("${DateTime.now().millis}", ScoreUtil.shouldResetHabitScore(habitWithTaskLogs))

        habit.taskRecurrence = CalendarUtil.RRULE_WEEKLY + "WE"
        Assert.assertFalse(ScoreUtil.shouldResetHabitScore(habitWithTaskLogs))

        habit.taskRecurrence = CalendarUtil.RRULE_WEEKLY + "TU"
        Assert.assertFalse(ScoreUtil.shouldResetHabitScore(habitWithTaskLogs))

        habit.taskRecurrence = CalendarUtil.RRULE_WEEKLY + "TH"
        Assert.assertTrue(ScoreUtil.shouldResetHabitScore(habitWithTaskLogs))
    }

    private fun createTaskLogTest(timestamp : Long) : TaskLog {
        val taskLog = TaskLog()
        taskLog.timestamp = timestamp
        return taskLog
    }
}