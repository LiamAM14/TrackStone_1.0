package es.unex.trackstone10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.unex.trackstone10.ui.decks.DecksFragment
import es.unex.trackstone10.ui.favorites.FavoritesFragment
import es.unex.trackstone10.ui.home.HomeFragment
import es.unex.trackstone10.ui.profile.ProfileFragment

class ButtonNavigationMenuActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val favoritesFragment = FavoritesFragment()
    private val decksFragment = DecksFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_navigation_menu)
        replaceFragment(homeFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_favorites -> replaceFragment(favoritesFragment)
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_decks -> replaceFragment(decksFragment)
                R.id.ic_profile -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}