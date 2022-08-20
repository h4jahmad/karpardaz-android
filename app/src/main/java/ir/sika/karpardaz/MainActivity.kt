package ir.sika.karpardaz

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.sika.karpardaz.databinding.ActivityMainBinding
import ir.sika.karpardaz.ui.BaseActivity
import ir.sika.karpardaz.ui.BindingInitializer

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

	override val bindingInitializer: BindingInitializer = ActivityMainBinding::inflate

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(binding.root)

		val navView: BottomNavigationView = binding.navView

		val navController = findNavController(R.id.nav_host_fragment_activity_main)

		navView.setupWithNavController(navController)
		navView.selectedItemId = R.id.navigation_cost
	}

}