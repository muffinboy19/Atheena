package com.example.nossier

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
//4

class Homescreen : AppCompatActivity() {

    private val profileFragment = profile()
    private val statsFragment = stats()
    private val createFragment = create()
    private val calendarFragment = calender()
    private val entriesFragment = entreis()
    private var createFragmentElevation: Float = 0f

    private   lateinit var fragmentContainer :FrameLayout
    private   lateinit var fragmentContainer2 :FrameLayout
    private lateinit var bottomNavigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        createFragmentElevation = resources.getDimension(R.dimen.create_fragment_elevation)
        fragmentContainer = findViewById(R.id.fragment_container)
        fragmentContainer2 = findViewById(R.id.boxo)

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

        replaceFragment(entriesFragment)
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        // Adjust elevation for Create fragment, and restore for other fragments
        if (fragment is create) {
            fragmentContainer2.elevation = createFragmentElevation
            transaction.add(R.id.boxo, fragment)
        } else {
            fragmentContainer2.elevation = 0f
            transaction.replace(R.id.boxo, fragment)// Restore default elevation for other fragments
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }




}
