package tech.appclub.loanmanager

import android.content.Context
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.contracts.CountryListContract
import tech.appclub.loanmanager.data.CountryData
import tech.appclub.loanmanager.utils.FileHelper

class CountryListModel internal constructor(
    private val context: Context
) : CountryListContract.Model {

    override fun getCountryList(onFinishedListener: CountryListContract.Model.OnFinishedListener) {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, CountryData::class.java)
        val jsonAdapter: JsonAdapter<List<CountryData>> = moshi.adapter(type)
        val countryData = jsonAdapter.fromJson(readJSON())
        if (countryData == null) {
            onFinishedListener.onFinished(emptyList())
            Log.d(LOG_TAG, countryData?.size.toString())
            return
        }
        Log.d(LOG_TAG, countryData.size.toString())
        onFinishedListener.onFinished(countryData)

    }

    private fun readJSON(): String {
        return FileHelper.getDataFromAssets(context, "countries.json")
    }

}