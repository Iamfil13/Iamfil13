package com.example.kotlinexample15

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class ConfirmationDialogFragment: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val articleType = arrayOf("Business", "Sports", "Medicine", "Android", "Science", "Culture")
        val screensSelect = mutableListOf<String>()

        return AlertDialog.Builder(requireContext())
            .setTitle("Select an article")
            .setMultiChoiceItems(
                articleType, null
            ) { _, which, isChecked ->
                if (isChecked) {
                    screensSelect.add(articleType[which])
                } else if (screensSelect.contains(articleType[which])) {
                    screensSelect.remove(articleType[which])
                }
            }
            .setPositiveButton("Select") { _, _ ->
//                articleType = screensSelect
            }
            .show()
    }
}