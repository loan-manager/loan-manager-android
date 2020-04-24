package tech.appclub.loanmanager.utils

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import tech.appclub.loanmanager.R
import java.util.*

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

        fun showDateError(context: Context, date: Date?, error: TextView): Boolean {
            if (date == null) {
                error.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                return true
            } else {
                error.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
            }
            return false
        }

    }

}