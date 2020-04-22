package tech.appclub.loanmanager.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.FragmentUserBinding
import tech.appclub.loanmanager.utils.Constants

class UserFragment : Fragment() {

    private var position: Int = 0

    private lateinit var preferences: SharedPreferences
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        this.binding.user = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_back)
        }

        preferences =
            requireActivity().getSharedPreferences(Constants.COUNTRY_DATA, Context.MODE_PRIVATE)
        position = preferences.getInt(Constants.COUNTRY_POSITION, 0)

        this.binding.countries.adapter = CurrencySpinnerAdapter(
            requireContext()
            , (requireActivity() as MainActivity).readCountriesData()
        )
        this.binding.countries.setSelection(position)
    }

    fun update() { updateInfo() }

    private fun updateInfo() {
        val selectedPosition = this.binding.countries.selectedItemPosition

        val editor = preferences.edit()

        if (selectedPosition != position) {

            editor.remove(Constants.COUNTRY_POSITION)
            editor.remove(Constants.COUNTRY_CODE)

            val country = this.binding.countries.selectedItem as Country
            editor.putString(Constants.COUNTRY_CODE, country.code)
            editor.putInt(Constants.COUNTRY_POSITION, selectedPosition)
        }

        editor.apply()
    }


}
