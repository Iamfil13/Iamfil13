package com.example.kotlinexample10

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment


class LoginFragment : Fragment(R.layout.activity_login_fragment) {

    @SuppressLint("SetTextI18n")
    private var login: FormState = FormState(false, "Enter login")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val textView = requireView().findViewById<TextView>(R.id.textView)
        textView.text = login.message

        val button = requireView().findViewById<Button>(R.id.button)
        button.isEnabled = true//login.valid

        val checkBox = requireView().findViewById<CheckBox>(R.id.checkBox)
        val progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)

        checkBox.setOnClickListener {
            login.valid = loginCorrect()
            button.isEnabled = loginCorrect()
        }

        button.setOnClickListener {
            if (loginCorrect()) {
                progressBar.visibility = View.VISIBLE
                Handler().postDelayed({
                    progressBar.visibility = View.INVISIBLE
                    login.message = "Login correct"

                    (activity as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment())
                        .commit()
                    Toast.makeText(activity, "login ending", Toast.LENGTH_SHORT).show()
                }, 2000)

            } else {
                login.message = "Login incorrect"
                textView.text = login.message
            }
        }
    }


    private fun loginCorrect(): Boolean {
        return (requireView().findViewById<EditText>(R.id.editTextMail).text.toString() != ""
                && requireView().findViewById<EditText>(R.id.editTextPass).text.toString() != ""
                && requireView().findViewById<CheckBox>(R.id.checkBox).isChecked)
    }


}