package tech.appclub.loanmanager.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.FragmentAddLoanBinding
import tech.appclub.loanmanager.utils.Constants
import tech.appclub.loanmanager.utils.DateTimeUtils
import tech.appclub.loanmanager.utils.NumUtils.Companion.trimCommaOfString
import tech.appclub.loanmanager.utils.NumberTextWatcherForThousand
import tech.appclub.loanmanager.utils.ValidationUtils.Companion.isEmpty
import tech.appclub.loanmanager.utils.ValidationUtils.Companion.showDateError
import java.util.*

class AddLoanFragment : Fragment() {


    private lateinit var binding: FragmentAddLoanBinding
    private lateinit var calendar: Calendar

    private var receiveDate: Date? = null
    private var paymentDate: Date? = null

    private var receiveDateError: Boolean = false
    private var paymentDateError: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_loan, container, false)
        this.binding.addLoan = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_back)
        }
        (requireActivity() as MainActivity).binding.bottomNavigationView.visibility = View.GONE

        calendar = Calendar.getInstance()

        val adapter = CurrencySpinnerAdapter(
            requireContext(),
            (requireActivity() as MainActivity).readCountriesData()
        )

        this.binding.countries.adapter = adapter
        this.binding.countries.setSelection(
            (requireActivity() as MainActivity).preferences.getInt(
                Constants.COUNTRY_POSITION,
                0
            )
        )
        this.binding.holderAmountValue.addTextChangedListener(
            NumberTextWatcherForThousand(this.binding.holderAmountValue)
        )

        binding.borrowedRadioButton.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                binding.receivedDate.text = getString(R.string.select_borrowed_date)
                binding.paymentDate.text = getString(R.string.select_payment_date)
                receiveDate = null
                paymentDate = null
            }
        }

        binding.givenRadioButton.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                binding.receivedDate.text = getString(R.string.select_lent_date)
                binding.paymentDate.text = getString(R.string.select_receiving_date)
                receiveDate = null
                paymentDate = null
            }
        }
    }

    fun selectReceivedDate() {
        val dateListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.receivedDate.text = DateTimeUtils.formatDate(calendar.time)
                receiveDate = calendar.time

                if (receiveDateError) {
                    binding.receivedDate.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.colorAccent
                        )
                    )
                    receiveDateError = false
                }

            }
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()

    }

    fun selectPaymentDate() {

        val dateListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.paymentDate.text = DateTimeUtils.formatDate(calendar.time)
                paymentDate = calendar.time

                if (paymentDateError) {
                    binding.receivedDate.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.colorAccent
                        )
                    ) 
                    paymentDateError = false
                }

            }
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis - 1000
        datePickerDialog.show()
    }

    fun addLoanAction() {

        val name = this.binding.holderNameValue.text.toString()
        if (isEmpty(name, this.binding.holderNameField, requireContext())) return

        val amount = trimCommaOfString(this.binding.holderAmountValue.text.toString())
        if (isEmpty(amount, this.binding.holderAmountField, requireContext())) return

        val countryModel = this.binding.countries.selectedItem as Country
        val code = countryModel.code
        val country = countryModel.country
        val currency = countryModel.currency

        if (showDateError(requireContext(), receiveDate, this.binding.receivedDate)) {
            receiveDateError = true
            return
        }
        if (showDateError(requireContext(), paymentDate, this.binding.paymentDate)) {
            paymentDateError = true
            return
        }

        val situation: Int = if (this.binding.givenRadioButton.isChecked) 0 else 1

        val loan = Loan()
        loan.holder = name
        loan.amount = amount.toDouble()
        loan.code = code
        loan.country = country
        loan.currency = currency
        loan.receivedOn = receiveDate
        loan.paymentOn = paymentDate
        loan.status = 2
        loan.situation = situation
        loan.position = this.binding.countries.selectedItemPosition

        (requireActivity() as MainActivity).loanViewModel.insert(loan)
        findNavController().navigateUp()
    }

    fun cancel() {
        findNavController().navigateUp()
    }


}
