package com.example.kotlinexample10

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment: Fragment(R.layout.fragment_list) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private val itemSelectListener: ItemSelectListener?
        get() = activity?.let { it as ItemSelectListener }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
//                    (activity as MainActivity).supportFragmentManager.beginTransaction()
//                        .add(R.id.container2, DetailFragment.newInstance("sdf"))
//                        .commit()
                    onButtonClick(button.text.toString())
                }
            }
    }

    private fun onButtonClick(buttonText: String){
        itemSelectListener?.onItemSelect(buttonText)
    }

}