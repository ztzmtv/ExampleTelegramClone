package com.azmetov.telegram.utilites

import android.text.Editable
import android.text.TextWatcher
// класс в конструкторе принимает лямбду
class AppTextWatcher(val onSuccess: (Editable?) -> Unit) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
// запускает лямбду с параметром p0 в момент afterTextChanged
    override fun afterTextChanged(p0: Editable?) {
        onSuccess(p0)
    }
}