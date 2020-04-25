package tech.appclub.loanmanager.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.FragmentIntroBinding
import tech.appclub.loanmanager.utils.Constants

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_intro, container, false)
        this.binding.introFragment = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.hide()
        }

        (requireActivity() as MainActivity).binding.bottomNavigationView.visibility = View.GONE

        this.binding.countries.adapter = CurrencySpinnerAdapter(
            requireContext(),
            (requireActivity() as MainActivity).readCountriesData()
        )
    }


    fun moveToHome() {

        val countryModel = this.binding.countries.selectedItem as Country
        val code = countryModel.code

        val editor =
            requireActivity().getSharedPreferences(Constants.COUNTRY_DATA, MODE_PRIVATE).edit()
        editor.clear()
        editor.putInt(Constants.COUNTRY_POSITION, this.binding.countries.selectedItemPosition)
        editor.putString(Constants.COUNTRY_CODE, code)
        editor.apply()

        findNavController().navigate(R.id.intro_to_home)

    }

}
