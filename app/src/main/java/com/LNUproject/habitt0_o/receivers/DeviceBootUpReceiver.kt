package com.LNUproject.habitt0_o.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.LNUproject.habitt0_o.utils.SettingsUtil

class DeviceBootUpReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if(intent == null) return

        if(intent.action == Intent.ACTION_BOOT_COMPLETED) SettingsUtil.startNotificationServiceIfEnabled(context)
    }
}