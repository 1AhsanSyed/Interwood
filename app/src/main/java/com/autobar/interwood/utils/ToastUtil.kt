package com.ingenious.powergenerations.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.autobar.interwood.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.showToast(message: String){
    CoroutineScope(Dispatchers.Main).launch {
        val toast = Toast(this@showToast)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.CENTER, 0, 0)

        val inflater =getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.your_custom_toast, null)
        val textView: TextView = view.findViewById(R.id.toastTextView)
        textView.setText(message)
        toast.view = view;
        toast.show()
    }
}

fun Context.showDialog(message: String) {
    CoroutineScope(Dispatchers.Main).launch {
        MaterialAlertDialogBuilder(this@showDialog)
            .setMessage(message)
            .setPositiveButton("Remove") { dialog, which ->
                // Respond to positive button press
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Respond to positive button press
                dialog.dismiss()
            }
            .show()
    }
}