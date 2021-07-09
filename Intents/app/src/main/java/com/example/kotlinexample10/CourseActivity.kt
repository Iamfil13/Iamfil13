package com.example.kotlinexample10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_course.*

class CourseActivity: AppCompatActivity(R.layout.activity_course) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intentData()
    }

    private fun intentData(){
        intent.data?.lastPathSegment?.let { courseTest->
            courseTestTextView.text = courseTest
        }
    }
}