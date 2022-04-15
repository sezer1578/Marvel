package com.ozaltun.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ozaltun.marvel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.marvel_navigation)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (navController.popBackStack().not()) {
            val builder = AlertDialog.Builder(this)
                .setTitle(getString(R.string.exitApp))
                .setMessage(getString(R.string.exitQuestiom))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(getString(R.string.yes)) { dialogInterface, _ ->
                    dialogInterface.cancel()
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
                .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                    navController.graph =
                        navController.navInflater.inflate(R.navigation.marvel_nav_graph)
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}