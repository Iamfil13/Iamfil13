package com.example.kotlinexample10

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment

class MainFragment: AppCompatActivity (R.layout.fragment_main), ItemSelectListener {

    override fun onItemSelect(text: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container2, DetailFragment.newInstance(text))
            .commit()
    }

}