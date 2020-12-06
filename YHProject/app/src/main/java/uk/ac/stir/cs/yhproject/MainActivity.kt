package uk.ac.stir.cs.yhproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * This application is a converter program that enables the user to choose units to convert data to.
 * @author: 2634056
 */
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Create the tab layout and add all the fragments
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page1))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page2))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page3))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // Create the pager and adapter that will handle movement between the tabs
        val viewPager = findViewById<ViewPager>(R.id.pager)
        viewPager.offscreenPageLimit = 3
        val adapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        // Assign PageListener to modify the visible fragment to the clicked tab
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener
        {
            // When a different tab is selected we change the fragment
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
}