package tech.appclub.loanmanager.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.FragmentUserBinding
import tech.appclub.loanmanager.utils.Constants

class UserFragment : Fragment() {

    private var position: Int = 0
    private var name: String? = ""
    private lateinit var preferences: SharedPreferences
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        this.binding.user = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences =
            requireActivity().getSharedPreferences(Constants.USER_DATA_FILE, Context.MODE_PRIVATE)
        name = preferences.getString(Constants.USER_NAME, "")
        position = preferences.getInt(Constants.COUNTRY_POSITION, 0)

        this.binding.name.setText(name, TextView.BufferType.EDITABLE)
        this.binding.countries.adapter = CurrencySpinnerAdapter(
            requireContext()
            , (requireActivity() as MainActivity).readCountriesData()
        )
        this.binding.countries.setSelection(position)
    }

    fun update() { updateInfo() }

    private fun updateInfo() {
        val nameInput = this.binding.name.text.toString()
        val selectedPosition = this.binding.countries.selectedItemPosition

        if (nameInput == name && selectedPosition == position) {
            Toast.makeText(requireContext(), "Nothing to change", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = preferences.edit()

        if (nameInput != name) {
            editor.remove(Constants.USER_NAME)
            editor.putString(Constants.USER_NAME, nameInput)
        }

        if (selectedPosition != position) {

            editor.remove(Constants.COUNTRY_POSITION)
            editor.remove(Constants.CURRENCY_CODE)
            editor.remove(Constants.CURRENCY_LABEL)
            editor.remove(Constants.CURRENCY_COUNTRY)

            val country = this.binding.countries.selectedItem as Country
            editor.putString(Constants.CURRENCY_CODE, country.code)
            editor.putString(Constants.CURRENCY_LABEL, country.currency)
            editor.putString(Constants.CURRENCY_COUNTRY, country.country)
            editor.putInt(Constants.COUNTRY_POSITION, selectedPosition)
        }

        editor.apply()
    }


}
