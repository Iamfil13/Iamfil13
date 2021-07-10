package com.example.kotlinexample10

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CourseActivity: AppCompatActivity(R.layout.activity_course) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intentData()
    }

    private fun intentData(){
        intent.data?.lastPathSegment?.let { courseTest->
            findViewById<TextView>(R.id.courseTestTextView).text = courseTest
        }
    }
}