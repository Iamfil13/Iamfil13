package com.example.kotlinexample10

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.second_activity.*


class SecondActivity : AppCompatActivity(R.layout.second_activity) {

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        buttonCall.setOnClickListener {
            val phoneNumber = phoneNumber.text.toString()
            val isPhoneValid = Patterns.PHONE.matcher(phoneNumber).matches()

            val isPhonePermission = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED

            if (!isPhonePermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE), 13
                )
            } else {
                if (!isPhoneValid) {
                    toast("Wrong number entered")
                } else {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:$phoneNumber")
                        putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber)
                    }
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivityForResult(intent, PHONE_REQUEST_CODE)
                    } else {
                        toast("No app to call")
                    }
                }
            }
        }

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHONE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                toast("call successful")
            } else {
                toast("there was no call")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val PHONE_REQUEST_CODE = 123
    }
}

