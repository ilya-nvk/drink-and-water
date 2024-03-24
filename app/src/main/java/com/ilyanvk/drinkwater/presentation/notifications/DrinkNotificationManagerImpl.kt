package com.ilyanvk.drinkwater.presentation.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Notification
import java.util.Calendar

class DrinkNotificationManagerImpl(
    private val context: Context
) : DrinkNotificationManager {
    override fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val name = context.getString(R.string.notification_channel_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(DrinkNotificationManager.CHANNEL_ID, name, importance)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun setNotification(notification: Notification) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                return
            }
        }

        val notificationIntent =
            Intent(context, NotificationBroadcastReceiver::class.java).apply {
                putExtra("notificationId", notification.id.hashCode())
            }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notification.id.hashCode(),
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmAt = getFirstAlarmTime(notification.time)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmAt,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Log.d(TAG, "Notification set for $alarmAt")
    }

    override fun cancelNotification(notificationId: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(context, NotificationBroadcastReceiver::class.java).apply {
            putExtra("notificationId", notificationId.hashCode())
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId.hashCode(),
            notificationIntent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
        }

        Log.d(TAG, "Notification cancelled")
    }

    private fun getFirstAlarmTime(notificationTime: Long): Long {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (calendar.timeInMillis + notificationTime <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return calendar.timeInMillis + notificationTime
    }

    private companion object {
        private const val TAG = "DrinkNotificationManager"
    }
}
