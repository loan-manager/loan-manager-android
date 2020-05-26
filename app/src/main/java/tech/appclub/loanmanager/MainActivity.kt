package tech.appclub.loanmanager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.ActivityMainBinding
import tech.appclub.loanmanager.utils.Constants
import tech.appclub.loanmanager.utils.FileHelper
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import java.util.prefs.Preferences


class MainActivity : AppCompatActivity() {

    companion object {
        // const val LOG_TAG = "LM_LOG"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var loanViewModel: LoanViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Setting toolbar
        setSupportActionBar(this.binding.toolbar)

        // View Model
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)

        preferences = getSharedPreferences(Constants.COUNTRY_DATA, Context.MODE_PRIVATE)

        // Getting Navigation Host & Controller
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        // Setting App Bar Configuration
        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.home_destination,
                    R.id.history_destination,
                    R.id.paid_loan_destination
                )
            )

        // Setting BottomNavMenu
        this.binding.bottomNavigationView.setupWithNavController(navController)

        // Setting up Action Bar
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    fun readCountriesData(): List<Country> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Country::class.java)
        val jsonAdapter: JsonAdapter<List<Country>> = moshi.adapter(type)
        val jsonFile =
            FileHelper.getDataFromAssets(
                applicationContext,
                Constants.COUNTRIES_FILE
            )
        return jsonAdapter.fromJson(jsonFile) ?: return emptyList()
    }


}
