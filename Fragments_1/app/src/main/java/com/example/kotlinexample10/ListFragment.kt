package com.example.kotlinexample10

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {

    private val fragment: Fragment? = parentFragment
    private val parentActivity: Activity? = activity

    private val parent: ItemSelectListener? = fragment?.let { it as? ItemSelectListener }
        ?: parentActivity?.let { it as? ItemSelectListener }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    (activity as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.container2, DetailFragment.newInstance(button.text.toString()))
                        .addToBackStack(button.text.toString())
                        .commit()

                    //onButtonClick(button.text.toString())
                }
            }

    }

    private fun onButtonClick(buttonText: String) {
        parent?.onItemSelect(buttonText)
    }

}