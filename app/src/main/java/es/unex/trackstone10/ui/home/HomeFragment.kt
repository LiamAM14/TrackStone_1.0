package es.unex.trackstone10.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import es.unex.trackstone10.R
import es.unex.trackstone10.ui.home.fragments.CardBacksFragment
import es.unex.trackstone10.ui.home.fragments.CardsFragment
import es.unex.trackstone10.ui.home.fragments.HeroesFragment
import es.unex.trackstone10.ui.home.fragments.adapter.ViewPagerAdapter

class HomeFragment : Fragment() {

    private lateinit var myFragment: View
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = myFragment.findViewById(R.id.viewPager)
        tabLayout = myFragment.findViewById(R.id.tabLayout)

        return myFragment
    }

    // Call onActivityCreated method
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.browse)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.cardback)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.hero)

    }

    private fun setUpViewPager(viewPager: ViewPager) {

        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(CardsFragment(), "Cards")
        adapter.addFragment(CardBacksFragment(), "Backs")
        adapter.addFragment(HeroesFragment(), "Heroes")

        viewPager.adapter = adapter

    }
}