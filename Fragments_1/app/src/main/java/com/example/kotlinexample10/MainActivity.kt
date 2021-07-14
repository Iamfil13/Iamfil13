package com.example.kotlinexample10

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonFragment = findViewById<Button>(R.id.buttonFragment)
        buttonFragment.setOnClickListener {
            showFragment()
        }

    }

    private fun showFragment() {

        val alreadyHasFragment = supportFragmentManager.findFragmentById(R.id.container) != null

        if (!alreadyHasFragment) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        } else {
            Toast.makeText(this, "Fragment is shown", Toast.LENGTH_SHORT).show()
        }
    }

}
