package tech.appclub.loanmanager.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.loan_item_view.view.*
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.FragmentAddLoanBinding
import tech.appclub.loanmanager.utils.Constants
import tech.appclub.loanmanager.utils.NumberTextWatcherForThousand
import tech.appclub.loanmanager.utils.ValidationUtils.Companion.isEmpty
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddLoanFragment : Fragment() {


    private lateinit var binding: FragmentAddLoanBinding
    private lateinit var calendar: Calendar
    private lateinit var loanViewModel: LoanViewModel

    private var receiveDate: Date? = null
    private var paymentDate: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_loan, container, false)
        this.binding.addLoanFragment = this
        return binding.root
    }

    // TODO: SET SPINNER VALUE FROM PREFERENCES
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendar = Calendar.getInstance()

        val preferences =
            requireActivity().getSharedPreferences(Constants.USER_DATA_FILE, Context.MODE_PRIVATE)

        val country: Country? = Country()
        country?.code = preferences.getString(Constants.CURRENCY_CODE, "")
        country?.country = preferences.getString(Constants.CURRENCY_COUNTRY, "")
        country?.currency = preferences.getString(Constants.CURRENCY_LABEL, "")
        Log.d(LOG_TAG, country.toString())

        val adapter = CurrencySpinnerAdapter(
            requireContext(),
            (requireActivity() as MainActivity).readCountriesData()
        )

        this.binding.countries.adapter = adapter
        if (country != null) {
            this.binding.countries.setSelection(preferences.getInt(Constants.COUNTRY_POSITION, 0))
        }
        this.binding.holderAmountValue.addTextChangedListener(
            NumberTextWatcherForThousand(this.binding.holderAmountValue)
        )
    }

    fun selectReceivedDate() {
        val dateListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val dateFormat = "EEEE, dd MMMM, yyyy"
                val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
                binding.receivedDate.text = simpleDateFormat.format(calendar.time)
                receiveDate = calendar.time
                binding.paymentDateAction.visibility = View.VISIBLE
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
                val dateFormat = "EEEE, dd MMMM, yyyy"
                val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
                binding.paymentDate.text = simpleDateFormat.format(calendar.time)
                paymentDate = calendar.time
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

        if (showDateError(receiveDate, this.binding.receivedDateTitle)) return
        if (showDateError(paymentDate, this.binding.paymentDateTitle)) return

        val loan = Loan()
        loan.holder = name
        loan.amount = amount.toDouble()
        loan.code = code
        loan.country = country
        loan.currency = currency
        loan.receivedOn = receiveDate
        loan.paymentOn = paymentDate
        loan.status = 0

        loanViewModel.insert(loan)
        findNavController().navigateUp()

    }

    private fun trimCommaOfString(string: String): String {
        return if (string.contains(",")) {
            string.replace(",", "")
        } else {
            string
        }
    }

    private fun showDateError(date: Date?, error: TextView): Boolean {
        if (date == null) {
            error.setTextColor(getColor(requireContext(), android.R.color.holo_red_dark))
            return true
        } else {
            error.setTextColor(getColor(requireContext(), android.R.color.darker_gray))
        }
        return false
    }
}
