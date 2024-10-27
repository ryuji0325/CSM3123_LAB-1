package com.example.custombroadcastreceiverapp // Ensure this matches your package structure

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    private lateinit var myReceiver: MyCustomBroadcastReceiver // Declare the receiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout

        myReceiver = MyCustomBroadcastReceiver() // Initialize the receiver

        // Find the button by its ID
        val sendBroadcastButton = findViewById<Button>(R.id.sendBroadcastButton)

        // Set the button's click listener
        sendBroadcastButton.setOnClickListener {
            // Create an intent with the custom action
            val intent = Intent("com.example.CUSTOM_ACTION")
            // Send the broadcast
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        // Register the receiver with the intent filter
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(myReceiver, IntentFilter("com.example.CUSTOM_ACTION"))
    }

    override fun onStop() {
        super.onStop()
        // Unregister the receiver to prevent memory leaks
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
    }
}
