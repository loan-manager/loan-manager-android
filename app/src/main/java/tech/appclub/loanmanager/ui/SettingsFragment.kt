package tech.appclub.loanmanager.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.FragmentSettingsBinding
import tech.appclub.loanmanager.utils.Constants
import tech.appclub.loanmanager.viewmodel.LoanViewModel


class SettingsFragment : Fragment() {

    private var position: Int = 0

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var preferences: SharedPreferences
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        this.binding.user = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).binding.bottomNavigationView.visibility = View.GONE

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_back)
        }

        preferences =
            requireActivity().getSharedPreferences(Constants.COUNTRY_DATA, Context.MODE_PRIVATE)
        position = preferences.getInt(Constants.COUNTRY_POSITION, 0)
        val countryCode = preferences.getString(Constants.COUNTRY_CODE, "")

        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)

        loanViewModel.unpaidLoanCount.observe(requireActivity(), Observer {
            this.binding.totalCountUnpaidLoans.text = String.format("%d unpaid loan(s)", it)
        })

        loanViewModel.unpaidLoanGrandTotal.observe(requireActivity(), Observer {
            if (it == null) {
                this.binding.grandTotalUnpaidLoans.text = String.format("%s 0 TO PAY", countryCode)
                return@Observer
            }
            this.binding.grandTotalUnpaidLoans.text = String.format("%s %.2f", countryCode, it)
        })

        loanViewModel.paidLoanCount.observe(requireActivity(), Observer {
            this.binding.totalCountPaidLoans.text = String.format("%d paid loan(s)", it)
        })

        loanViewModel.paidLoanGrandTotal.observe(requireActivity(), Observer {
            if (it == null) {
                this.binding.grandTotalPaidLoans.text =
                    String.format("%s 0 LOANS PAID", countryCode)
                return@Observer
            }
            this.binding.grandTotalPaidLoans.text = String.format("%s %.2f", countryCode, it)
        })

        loanViewModel.allLoanCount.observe(requireActivity(), Observer {
            this.binding.totalCountLoans.text = String.format("%d total loan(s)", it)
        })

        this.binding.countries.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                if (pos == position) {
                    binding.updateButton.visibility = View.INVISIBLE
                } else {
                    binding.updateButton.visibility = View.VISIBLE
                }
            }

        }


        this.binding.countries.adapter = CurrencySpinnerAdapter(
            requireContext()
            , (requireActivity() as MainActivity).readCountriesData()
        )
        this.binding.countries.setSelection(position)
    }

    fun update() {
        showUpdateDialog()
    }

    fun deleteAllLoans() {
        showDeleteDialog()
    }

    private fun showDeleteDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Deleting all loans")
            .setMessage("This action will delete all loans. Do you want to proceed?")
            .setIcon(R.drawable.ic_warning)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                loanViewModel.deleteAll()
                Toast.makeText(requireContext(), "All loans deleted", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showUpdateDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update all currencies")
            .setMessage("This action will update all currencies. Do you want to proceed?")
            .setIcon(R.drawable.ic_warning)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                updateInfo()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun updateInfo() {
        val selectedPosition = this.binding.countries.selectedItemPosition

        val editor = preferences.edit()

        editor.remove(Constants.COUNTRY_POSITION)
        editor.remove(Constants.COUNTRY_CODE)

        val country = this.binding.countries.selectedItem as Country
        editor.putString(Constants.COUNTRY_CODE, country.code)
        editor.putInt(Constants.COUNTRY_POSITION, selectedPosition)

        editor.apply()
        loanViewModel.updateCurrency(country.code!!, country.currency!!, country.country!!, selectedPosition)

        findNavController().navigateUp()
    }


}
