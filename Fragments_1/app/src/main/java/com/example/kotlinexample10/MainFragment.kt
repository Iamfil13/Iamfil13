package com.example.kotlinexample10

import android.content.Context
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener {


    override fun onItemSelect(text: String) {

        val transaction = requireFragmentManager().beginTransaction()
        val count = requireFragmentManager().backStackEntryCount

        transaction.replace(R.id.container2, DetailFragment.newInstance(text))
        if (count == 0) {
            transaction.addToBackStack(text)
        }
        transaction.commit()
    }

}