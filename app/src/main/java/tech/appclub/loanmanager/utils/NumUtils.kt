package tech.appclub.loanmanager.utils

class NumUtils {

    companion object {

        fun trimCommaOfString(string: String): String {
            return if (string.contains(",")) {
                string.replace(",", "")
            } else {
                string
            }
        }

    }
}