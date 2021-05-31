package com.example.kotlinexample10

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        textView.setText(R.string.app_name)

        val editTextMail = findViewById<EditText>(R.id.editTextMail)
        val editTextPass = findViewById<EditText>(R.id.editTextPass)
        val button = findViewById<Button>(R.id.button)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnClickListener {
            button.isEnabled = editTextMail.text.toString() != "" && editTextPass.text.toString() != "" && checkBox.isChecked
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

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = s?.takeIf { it.isNotBlank() }
                    ?.let { name-> resources.getString(R.string.hello_string, name) }
            }
            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = editTextMail.text.toString() != "" && editTextPass.text.toString() != "" && checkBox.isChecked
            }
        })

        editTextPass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = editTextMail.text.toString() != "" && editTextPass.text.toString() != "" && checkBox.isChecked
            }
        })

    }
}