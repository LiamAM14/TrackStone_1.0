package es.unex.trackstone10.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import es.unex.trackstone10.R
import es.unex.trackstone10.ui.favorites.fragments.adapter.ViewPagerFavoritesAdapter
import es.unex.trackstone10.ui.favorites.fragments.CardBacksFavoritesFragment
import es.unex.trackstone10.ui.favorites.fragments.CardsFavoritesFragment
import es.unex.trackstone10.ui.favorites.fragments.HeroesFavoritesFragment

class FavoritesFragment : Fragment() {

    private lateinit var myFragment: View
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_favorites, container, false)

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

        val adapter = ViewPagerFavoritesAdapter(childFragmentManager)

        adapter.addFragment(CardsFavoritesFragment(), "Cards")
        adapter.addFragment(CardBacksFavoritesFragment(), "Backs")
        adapter.addFragment(HeroesFavoritesFragment(), "Heroes")

        viewPager.adapter = adapter

    }
}