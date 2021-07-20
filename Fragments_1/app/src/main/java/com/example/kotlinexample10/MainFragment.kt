package com.example.kotlinexample10

import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener {

    override fun onItemSelect(text: String) {

        val transaction = requireFragmentManager().beginTransaction()
//        if (requireView().findViewById<TextView>(R.id.textDetail) == null) {
//            transaction.addToBackStack("text")
//        }
        transaction.replace(R.id.container2, DetailFragment.newInstance(text))
        transaction.commit()
    }

}