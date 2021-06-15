package com.example.kotlinexample118

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener{
//            Toast.makeText(this, "Navigation", Toast.LENGTH_SHORT).show()
//        }
    }
}