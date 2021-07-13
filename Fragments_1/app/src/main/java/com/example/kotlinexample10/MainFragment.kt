package com.example.kotlinexample10

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainFragment: Fragment (R.layout.fragment_main), ItemSelectListener {


    override fun onItemSelect(text: String) {
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListFragment())
            .commit()
    }


}