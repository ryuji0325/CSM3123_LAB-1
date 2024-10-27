package com.example.musicplayerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to buttons
        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)

        // Start MusicService on Start button click
        startButton.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
        }

        // Stop MusicService on Stop button click
        stopButton.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent) // Stop the service
        }
    }
}
