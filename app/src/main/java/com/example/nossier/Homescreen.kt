package com.example.nossier

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
    private   lateinit var fragmentContainer :FrameLayout
    private lateinit var bottomNavigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

//    private fun replaceFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment)
//
//        if (fragment == createFragment) {
//            fragmentContainer.elevation = 10f // Set a higher elevation for the "Create" fragment
//        } else {
//            fragmentContainer.elevation = 0f // Reset the elevation for other fragments
//        }
//
//        transaction.commit()
//    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)

        if (fragment == createFragment) {
            bottomNavigation.translationZ = -100f // Move the bottom navigation 100 pixels behind the create fragment
        } else {
            bottomNavigation.translationZ = 0f // Reset the translationZ of the bottom navigation
        }



        transaction.commit()
    }


}
