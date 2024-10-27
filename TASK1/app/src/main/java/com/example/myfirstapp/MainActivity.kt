package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)


        val button: Button
        val buttonNavigate: Button = findViewById(R.id.buttonNavigate)
        val textView: TextView

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        {v, insets -> val systemBars =insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom);insets
        }
        textView = findViewById(R.id.textView)
            button = findViewById(R.id.button)
            button.setOnClickListener{
                textView.text ="Hello"
                Toast.makeText(this, "Button was clicked!", Toast.LENGTH_SHORT).show()
            }
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("EXTRA_MESSAGE", "Hello from MainActivity!")
            startActivity(intent);
        }



    }


}

