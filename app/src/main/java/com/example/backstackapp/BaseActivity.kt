package com.example.backstackapp

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    private lateinit var activityManager: ActivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    }

    protected fun startActivity(context: Context, targetActivity: Class<*> ) {
        val intent = Intent(context, targetActivity)
        if (targetActivity == Activity_B::class.java) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    protected fun getAppTaskState(): String {
        val stringBuilder = StringBuilder()
        val totalNumberOfTasks = activityManager.appTasks.size
        stringBuilder.append("\nTotal Number of Tasks: $totalNumberOfTasks\n\n")

        val taskInfo : List<ActivityManager.AppTask> = activityManager.appTasks

        for( info in taskInfo) {
            stringBuilder.append("Task Id: ${info.taskInfo.taskId} Number of Activities : ${info.taskInfo.numActivities} \n")
            stringBuilder.append("TopActivity: ${info.taskInfo.topActivity.toString()
                .replace("ComponentInfo{com.example.backstackapp/com.example.backstackapp.", "")}\n")
            stringBuilder.append("BaseActivity: ${info.taskInfo.baseActivity.toString()
                .replace("ComponentInfo{com.example.backstackapp/com.example.backstackapp.", "")}\n")
            stringBuilder.append("\n\n")
        }

        return stringBuilder.toString()
    }

}