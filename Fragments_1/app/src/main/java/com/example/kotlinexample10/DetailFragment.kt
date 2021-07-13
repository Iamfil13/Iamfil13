package com.example.kotlinexample10

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textDetail = requireView().findViewById<TextView>(R.id.textDetail)
    }

}