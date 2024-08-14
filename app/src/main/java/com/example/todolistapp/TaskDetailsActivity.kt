package com.example.todolistapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Build
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        // Retrieve the task data from the intent
        val task = intent.getStringExtra("EXTRA_TASK")

        // Find the TextView and set the task text
        val textViewTaskDetails = findViewById<TextView>(R.id.textViewTaskDetails)
        textViewTaskDetails.text = task

        // Show notification with the task details
        showNotification(task ?: "No task provided")
    }

    private fun showNotification(task: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "task_channel",
                "Task Notifications",
                NotificationManager.IMPORTANCE_HIGH // Set to HIGH for testing
            ).apply {
                description = "Channel for task notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Create an intent to open TaskDetailsActivity when the notification is tapped
        val intent = Intent(this, TaskDetailsActivity::class.java).apply {
            putExtra("EXTRA_TASK", task)
        }

        // Create a PendingIntent with FLAG_IMMUTABLE
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Build the notification
        val notification = NotificationCompat.Builder(this, "task_channel")
            .setContentTitle("Task Reminder")
            .setContentText(task)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Use a built-in icon for testing
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        // Show the notification
        notificationManager.notify(1, notification)
    }
}
