package com.example.kotlinexample10

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    onButtonClick(button.text.toString())
                }
            }
    }

    private fun onButtonClick(buttonText: String) {
        (parentFragment as ItemSelectListener).onItemSelect(buttonText)
    }

}