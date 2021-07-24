package com.example.kotlinexample15

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class DialogFragment: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val articleType = arrayOf("Business", "Sports", "Medicine", "Android", "Science", "Culture")
            val selectedItems = ArrayList<Int>()
            val builder = AlertDialog.Builder(it)

            builder.setTitle("Select an article")

                .setMultiChoiceItems(articleType, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            selectedItems.add(which)
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which)
                        }
                    })

                .setPositiveButton("Select",
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}