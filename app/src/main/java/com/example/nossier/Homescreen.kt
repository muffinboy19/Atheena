package com.example.nossier

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homescreen : AppCompatActivity() {

    private val profileFragment = profile()
    private val statsFragment = stats()
    private val createFragment = create()
    private val calendarFragment = calender()
    private val entriesFragment = entreis()
    private var createFragmentElevation: Float = 0f

    private   lateinit var fragmentContainer :FrameLayout
    private lateinit var bottomNavigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        createFragmentElevation = resources.getDimension(R.dimen.create_fragment_elevation)
        fragmentContainer = findViewById(R.id.fragment_container)
        bottomNavigation = findViewById(R.id.bottom_navigation)
         bottomNavigation  = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> replaceFragment(profileFragment)
                R.id.menu_stats -> replaceFragment(statsFragment)
                R.id.menu_create -> replaceFragment(createFragment)
                R.id.menu_calendar -> replaceFragment(calendarFragment)
                R.id.menu_entries -> replaceFragment(entriesFragment)
            }
            true
        }

        replaceFragment(profileFragment)
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        // Adjust elevation for Create fragment, and restore for other fragments
        if (fragment is create) {
            fragmentContainer.elevation = createFragmentElevation
        } else {
            fragmentContainer.elevation = 0f // Restore default elevation for other fragments
        }
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }




}
