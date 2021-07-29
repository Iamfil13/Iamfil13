package com.example.kotlinexample15

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class ConfirmationDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val onClick = activity as OnClickPositiveButton
        val tags = arrayOf("Business", "Sports", "Medicine", "Android", "Science", "Culture")
        val checkedItemsForDialog: BooleanArray =
            requireArguments().getBooleanArray(KEY_SCREENS) ?: error("Error")

        val checkedItems = booleanArrayOf(false, false, false, false, false, false)
        for (i in checkedItemsForDialog.indices) {
            checkedItems[i] = checkedItemsForDialog[i]
        }


        return AlertDialog.Builder(requireContext())
            .setTitle("Article type")
            .setMultiChoiceItems(tags, checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }
            .setPositiveButton("Select") { _, _ ->
                onClick.onClickSelect(checkedItems)
            }
            .create()


    }

    companion object {

        private const val KEY_SCREENS = "checked items key"

        fun newInstance(
            checkedItems: BooleanArray
        ): ConfirmationDialogFragment {
            return ConfirmationDialogFragment().withArguments {
                putBooleanArray(KEY_SCREENS, checkedItems)
            }
        }

    }


}