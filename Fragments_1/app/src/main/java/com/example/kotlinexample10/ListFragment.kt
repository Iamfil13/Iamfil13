package com.example.kotlinexample10

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment: Fragment(R.layout.fragment_list), ItemSelectListener {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private val itemSelectListener: ItemSelectListener?
        get() = activity?.let { it as ItemSelectListener }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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

    private fun onButtonClick(buttonText: String){
        itemSelectListener?.onItemSelect(buttonText)

//            .supportFragmentManager.beginTransaction()
//            .replace(R.id.container, newInstance(buttonText))
//            .commit()
    }


    companion object {

        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): Fragment {
            return Fragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }

    override fun onItemSelect(text: String) {
        TODO("Not yet implemented")
    }


}