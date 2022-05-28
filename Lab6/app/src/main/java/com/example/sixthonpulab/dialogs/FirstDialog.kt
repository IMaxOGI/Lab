package com.example.sixthonpulab.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sixthonpulab.MainActivity

class FirstDialog( private val component: MainActivity.MyComponent
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Number")
            .setMessage("You have chosen number: ${component.randNumber}")
            .setPositiveButton("Ok", null)
            .create()
    }

}