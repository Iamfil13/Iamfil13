package com.example.kotlinexample10

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    private var login: FormState = FormState(false, "Enter login")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        if (savedInstanceState != null) {
            login = savedInstanceState.getParcelable(KEY_LOGIN) ?: error("Error")
        }

        textView.text = login.message
        button.isEnabled = login.valid

        anr.setOnClickListener {
            Thread.sleep(10000)
        }

        checkBox.setOnClickListener {
            login.valid = loginCorrect()
            button.isEnabled = loginCorrect()
        }

        button.setOnClickListener {
            if (loginCorrect()) {
                progressBar.visibility = View.VISIBLE
                Handler().postDelayed({
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "login ending", Toast.LENGTH_SHORT).show()
                    login.message = "Login correct"
                    textView.text = login.message
                    startActivity(
                        Intent(
                            this, SecondActivity::class.java
                        )
                    )
                }, 2000)
                onDestroy()
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

    companion object {
        private const val KEY_LOGIN = "counter"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_LOGIN, login)
    }

}
