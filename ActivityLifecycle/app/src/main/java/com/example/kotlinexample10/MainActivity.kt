package com.example.kotlinexample10

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    fun loginCorrect(): Boolean {
        return if(findViewById<EditText>(R.id.editTextMail).text.toString() != ""
            && findViewById<EditText>(R.id.editTextPass).text.toString() != ""
            && findViewById<CheckBox>(R.id.checkBox).isChecked) {
            findViewById<TextView>(R.id.textView).text = ""
            true
        } else {
            findViewById<TextView>(R.id.textView).text = "Login incorrect"
            false
        }
    }

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val editTextMail: EditText = findViewById(R.id.editTextMail)
        val editTextPass: EditText = findViewById(R.id.editTextPass)
        val button: Button = findViewById(R.id.button)
        val anr: Button = findViewById(R.id.anr)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val checkBox: CheckBox = findViewById(R.id.checkBox)

        anr.setOnClickListener {
            Thread.sleep(10000)
        }

        checkBox.setOnClickListener {
            button.isEnabled = loginCorrect()
        }

        button.setOnClickListener {
            textView.text = ""
            editTextMail.setText("")
            editTextPass.setText("")
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
            checkBox.isChecked = false
            Handler().postDelayed({
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "login ending", Toast.LENGTH_SHORT).show() }, 2000)
        }

        editTextMail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = loginCorrect()
            }
        })

        editTextPass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = loginCorrect()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        DebugLogger.d(tag,"onStart")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.d(tag,"onResume")
    }

    override fun onPause() {
        super.onPause()
        DebugLogger.d(tag,"onPause")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.d(tag,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag,"onDestroy")
    }

    companion object {
        private const val KEY_COUNTER = "counter"
        private const val KEY_BUTTON = "button"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_COUNTER, loginCorrect())
        val button: Button = findViewById(R.id.button)
        outState.putBoolean(KEY_BUTTON, button.isEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(KEY_COUNTER)){
            loginCorrect()
        }
        if (savedInstanceState.getBoolean(KEY_BUTTON)){
            val button: Button = findViewById(R.id.button)
            button.isEnabled = true
        }
    }
}

object DebugLogger {
    fun d(tag: String, message: String){
        if (BuildConfig.DEBUG){
            Log.d(tag, message)
        }
    }
}
