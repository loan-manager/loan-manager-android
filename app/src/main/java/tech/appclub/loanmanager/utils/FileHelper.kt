package tech.appclub.loanmanager.utils

import android.content.Context

class FileHelper {

    companion object {

        fun getDataFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }

    }
}