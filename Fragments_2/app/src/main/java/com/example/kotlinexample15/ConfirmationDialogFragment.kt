package com.example.kotlinexample15

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class ConfirmationDialogFragment: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val articleType = arrayOf("Business", "Sports", "Medicine", "Android", "Science", "Culture")


        return AlertDialog.Builder(requireContext())
            .setTitle("Select an article")
            .setMultiChoiceItems(
                articleType, null
            ) { _, which, isChecked ->
                if (isChecked) {

                } else  {

                }
            }
            .setPositiveButton("Select") { _, _ ->

            }
            .show()
    }




}