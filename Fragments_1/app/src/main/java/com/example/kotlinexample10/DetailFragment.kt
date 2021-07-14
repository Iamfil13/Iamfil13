package com.example.kotlinexample10

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textDetail = requireView().findViewById<TextView>(R.id.textDetail)
        textDetail.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {

        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }

}