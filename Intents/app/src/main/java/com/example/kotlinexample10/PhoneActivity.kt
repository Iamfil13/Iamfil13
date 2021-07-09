package com.example.kotlinexample10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phone.*


class PhoneActivity : AppCompatActivity(R.layout.activity_phone) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNumberFromIntent()

    }

    private fun setNumberFromIntent() {
        val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        phoneTextView.text = number ?: "Phone number is not set"
    }
}