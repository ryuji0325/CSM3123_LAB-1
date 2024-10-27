package com.example.musicplayerapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Create a notification for the foreground service
        val notification = createNotification()

        // Start the service in the foreground
        startForeground(1, notification)

        // Initialize and start media playback
        playMusic()

        return START_STICKY
    }

    private fun playMusic() {
        // Initialize the media player with an audio resource
        mediaPlayer = MediaPlayer.create(this, R.raw.song) // Replace with your audio file name (without extension)
        mediaPlayer.isLooping = true // Loop the music if desired
        mediaPlayer.start() // Start playback
    }

    private fun createNotification(): Notification {
        val channelId = "MusicServiceChannel"
        val channelName = "Music Service"

        // Create a notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        // Build the notification
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Music Player")
            .setContentText("Playing music")
            .setSmallIcon(R.drawable.ic_music_note) // Ensure this icon exists in res/drawable
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release() // Release resources when the service is destroyed
        }
    }
}

