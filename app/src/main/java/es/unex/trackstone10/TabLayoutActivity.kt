package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import es.unex.trackstone10.ui.home.fragments.CardBacksFragment
import es.unex.trackstone10.ui.home.fragments.CardsFragment
import es.unex.trackstone10.ui.home.fragments.HeroesFragment
import es.unex.trackstone10.ui.home.fragments.adapter.ViewPagerAdapter

class TabLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        setUpTabs()
    }

    private fun setUpTabs() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        adapter.addFragment(CardsFragment(), "Cards")
        adapter.addFragment(CardBacksFragment(), "Backs")
        adapter.addFragment(HeroesFragment(), "Heroes")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.browse)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.cardback)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.hero)

    }
}