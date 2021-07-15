package com.example.kotlinexample10

import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener {

    override fun onItemSelect(text: String) {
        fragmentManager?.beginTransaction()
            ?.add(R.id.container2, DetailFragment.newInstance(text))
            ?.commit()
    }


}