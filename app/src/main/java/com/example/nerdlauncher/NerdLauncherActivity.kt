package com.example.nerdlauncher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nerdlauncher.databinding.ActivityNerdLauncherBinding


class NerdLauncherActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNerdLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appRecyclerView.layoutManager = LinearLayoutManager(this)

        setupAdapter(binding)
    }

    private fun setupAdapter(binding: ActivityNerdLauncherBinding) {
        val startupIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val activities = packageManager.queryIntentActivities(startupIntent, 0)
        activities.sortWith { a, b ->
            String.CASE_INSENSITIVE_ORDER.compare(
                a.loadLabel(packageManager).toString(),
                b.loadLabel(packageManager).toString()
            )
        }

        Log.i(TAG, "Found ${activities.size} activities")
        binding.appRecyclerView.adapter = ActivityAdapter(activities)
    }


    companion object {
        private const val TAG = "NerdLauncherActivity"
    }
}