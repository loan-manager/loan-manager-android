package tech.appclub.loanmanager.utils

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import tech.appclub.loanmanager.R

class ValidationUtils {

    companion object {

        fun isEmpty(
            input: String,
            inputLayout: TextInputLayout,
            context: Context
        ): Boolean {
            if (input.isBlank() || input.isEmpty()) {
                inputLayout.error = context.resources.getString(R.string.empty_error)
                return true
            } else {
                inputLayout.error = null
            }
            return false
        }

    }

}