package com.example.kotlinexample10

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainFragment : AppCompatActivity(R.layout.fragment_main), ItemSelectListener {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        supportFragmentManager.findFragmentById(R.id.container)?.onDestroy()
    }

    override fun onItemSelect(text: String) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container2, DetailFragment.newInstance(text))
            .addToBackStack(text)
            .commit()
    }


}