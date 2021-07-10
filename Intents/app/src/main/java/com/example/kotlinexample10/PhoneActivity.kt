package com.example.kotlinexample10

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class PhoneActivity : AppCompatActivity(R.layout.activity_phone) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNumberFromIntent()

    }

    private fun setNumberFromIntent() {
        val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        findViewById<TextView>(R.id.phoneTextView).text = number ?: "Phone number is not set"
    }
}