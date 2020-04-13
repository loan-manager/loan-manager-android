package tech.appclub.loanmanager.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {

    companion object {

        fun formatDate(date: Date): String {
            val dateFormat = "EEEE, dd MMMM, yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
            return simpleDateFormat.format(date)
        }

    }
}