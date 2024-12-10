package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var navDrawer: DrawerLayout
    private var highScore2 = 0
    private var highScore3 = 0
    private var highScore4 = 0
    private var shareMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.action_bar))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController.graph
        navDrawer = findViewById(R.id.drawer_layout)
        appBarConfig = AppBarConfiguration(navGraph, navDrawer)
        setupActionBarWithNavController(navController, appBarConfig)
        val navigationView: NavigationView = findViewById(R.id.drawer_view)
        navigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    fun getHighScore2(): Int {
        return highScore2
    }

    fun setHighScore2(newNum: Int) {
        highScore2 = newNum
    }

    fun getHighScore3(): Int {
        return highScore3
    }

    fun setHighScore3(newNum: Int) {
        highScore3 = newNum
    }

    fun getHighScore4(): Int {
        return highScore4
    }

    fun setHighScore4(newNum: Int) {
        highScore4 = newNum
    }



    override fun onSupportNavigateUp(): Boolean {
        val success: Boolean = navController.navigateUp(appBarConfig)
        return success || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {   //Share Stuff
        if (item.itemId == R.id.menu_item_fragment_four) {
            navController.navigate(R.id.navigate_to_fourth_global)
            return true
        } else if (item.itemId == R.id.menu_item_share) {
            val shareIntent: Intent = createIntent(shareMessage)
            startActivity(shareIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun assignShareMessage(message: String) {
        shareMessage = message
    }

    private fun createIntent(stringToShare: String): Intent {
        val intent: Intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, stringToShare)
        return intent
    }

    override fun onDestinationChanged(   //Locks out drawer
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id == R.id.firstFragment) {
            navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else {
            navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }
}