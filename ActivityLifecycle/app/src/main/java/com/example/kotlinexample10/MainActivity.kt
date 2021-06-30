package com.example.kotlinexample10

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    private val tag = "MainActivity"
    private var login: FormState = FormState(false, "Enter login")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            login = savedInstanceState.getParcelable(KEY_LOGIN) ?: error("Error")
        }

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = login.message
        val button: Button = findViewById(R.id.button)
        val anr: Button = findViewById(R.id.anr)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        anr.setOnClickListener {
            Thread.sleep(10000)
        }

        button.setOnClickListener {
            if (loginCorrect()) {
                progressBar.visibility = View.VISIBLE
                Handler().postDelayed({
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "login ending", Toast.LENGTH_SHORT).show()
                    login.message = "Login correct"
                    textView.text = login.message
                }, 2000)
            } else {
                login.message = "Login incorrect"
                textView.text = login.message
            }
        }
    }

    private fun loginCorrect(): Boolean {
        return (findViewById<EditText>(R.id.editTextMail).text.toString() != ""
                && findViewById<EditText>(R.id.editTextPass).text.toString() != ""
                && findViewById<CheckBox>(R.id.checkBox).isChecked)
    }

    override fun onStart() {
        super.onStart()
        DebugLogger.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        DebugLogger.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag, "onDestroy")
    }

    object DebugLogger {
        fun d(tag: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, message)
            }
        }
    }

    companion object {
        private const val KEY_LOGIN = "counter"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_LOGIN, login)
    }

}
