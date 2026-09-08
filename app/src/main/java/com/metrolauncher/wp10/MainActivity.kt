package com.metrolauncher.wp10

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var textClock: TextView
    private val clockHandler = Handler(Looper.getMainLooper())
    private lateinit var clockRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textClock = findViewById(R.id.textClock)
        val recyclerTiles: RecyclerView = findViewById(R.id.recyclerTiles)
        val textAllApps: TextView = findViewById(R.id.textAllApps)

        // Luoi 4 cot kieu Live Tile cua Windows Phone 10
        recyclerTiles.layoutManager = GridLayoutManager(this, 4)

        val apps = AppRepository.getLaunchableApps(this)
        // Ghim toi da 20 app dau tien len man hinh chinh, giong Start Screen
        val pinnedApps = apps.take(20)

        recyclerTiles.adapter = TileAdapter(pinnedApps) { app ->
            AppRepository.launchApp(this, app.packageName)
        }

        textAllApps.setOnClickListener {
            startActivity(Intent(this, AppDrawerActivity::class.java))
        }

        startClock()
    }

    private fun startClock() {
        clockRunnable = object : Runnable {
            override fun run() {
                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                textClock.text = sdf.format(Date())
                clockHandler.postDelayed(this, 1000)
            }
        }
        clockHandler.post(clockRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        clockHandler.removeCallbacks(clockRunnable)
    }

    override fun onBackPressed() {
        // Man hinh chinh cua launcher: nut Back se dua app ve nen, khong thoat/crash
        moveTaskToBack(true)
    }
}
