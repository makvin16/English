package com.zm.englishtraining.core.ext

import android.content.Context
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

@RequiresApi(Build.VERSION_CODES.CUPCAKE)
fun Fragment.hideKeyboard() {
    requireActivity().currentFocus?.let { view ->
        val manager = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}