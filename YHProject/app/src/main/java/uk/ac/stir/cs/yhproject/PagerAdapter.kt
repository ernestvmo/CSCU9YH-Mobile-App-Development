package uk.ac.stir.cs.yhproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class PagerAdapter (fragmentManager: FragmentManager?, private val numOfTabs: Int) : FragmentStatePagerAdapter (fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * This method returns the Fragment at the  given position.
     * @param position The position in the tab layout.
     * @return The fragment associated with the selected tab.
     */
    override fun getItem(position: Int) : Fragment {
        return when (position) {// Switch to the fragment associated with the tab position
            0 -> Page1Fragment()
            1 -> Page2Fragment()
            2 -> Page3Fragment()
            else -> Fragment()
        }
    }

    /**
     * This method returns the number of tabs in the Layout.
     * @return The number of tabs.
     */
    override fun getCount(): Int {
        return numOfTabs
    }
}