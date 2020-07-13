package com.example.bookofrecipes.activities.activity_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.bookofrecipes.R
import com.example.bookofrecipes.fragments.fragment_favorites.FavoritesFragment
import com.example.bookofrecipes.fragments.fragment_news.NewsFragment
import com.example.bookofrecipes.fragments.fragment_search.SearchFragment
import com.example.bookofrecipes.fragments.fragment_settings.SettingsFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class NavigationActivity : AppCompatActivity() {

    lateinit var newsFragment: NewsFragment
    lateinit var searchFragment: SearchFragment
    lateinit var favoritesFragment: FavoritesFragment
    lateinit var settingsFragment: SettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val bottomNavigation: ChipNavigationBar =
            findViewById<ChipNavigationBar>(R.id.navigation_bar)

        bottomNavigation.setItemSelected(R.id.news, true).run {
            newsFragment = NewsFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_navigation, newsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item) {
                R.id.news -> {
                    newsFragment = NewsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_navigation, newsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.search -> {
                    searchFragment = SearchFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_navigation, searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.favorites -> {
                    favoritesFragment =
                        FavoritesFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_navigation, favoritesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.settings -> {
                    settingsFragment = SettingsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_navigation, settingsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
        }

    }
}