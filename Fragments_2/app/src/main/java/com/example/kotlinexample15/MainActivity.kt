package com.example.kotlinexample15

import android.os.Bundle
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var screens: List<OnboardScreen> = listOf(
        OnboardScreen(
            textRes = R.string.business,
            bgColorRes = android.R.color.holo_orange_dark,
            drawableRes = R.drawable.ic_money
        ),
        OnboardScreen(
            textRes = R.string.sport,
            bgColorRes = android.R.color.holo_green_light,
            drawableRes = R.drawable.ic_sports
        ),
        OnboardScreen(
            textRes = R.string.medicine,
            bgColorRes = R.color.teal_200,
            drawableRes = R.drawable.ic_medical
        ),
        OnboardScreen(
            textRes = R.string.android,
            bgColorRes = android.R.color.holo_orange_light,
            drawableRes = R.drawable.ic_android
        ),
        OnboardScreen(
            textRes = R.string.science,
            bgColorRes = android.R.color.holo_purple,
            drawableRes = R.drawable.ic_science
        ),
        OnboardScreen(
            textRes = R.string.culture,
            bgColorRes = android.R.color.holo_red_light,
            drawableRes = R.drawable.ic_deck
        )

    )

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = OnboardAdapter(screens, this)
        viewPager.adapter = adapter
        showTabLayout()


        viewPager.setPageTransformer { page, position ->
            when {
                position < -1 || position > 1 -> {
                    page.alpha = 0F
                }
                position <= 0 -> {
                    page.alpha = 1 + position
                }
                position <= 1 -> {
                    page.alpha = 1 - position
                }
            }
        }

        findViewById<Button>(R.id.showDialog).setOnClickListener {
            showDialogFragment()
        }

    }


    private fun showTabLayout() {
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (screens[position]) {
                screens[0] -> tab.setIcon(screens[0].drawableRes).text = "Business"
                screens[1] -> tab.setIcon(screens[1].drawableRes).text = "Sports"
                screens[2] -> tab.setIcon(screens[2].drawableRes).text = "Medicine"
                screens[3] -> tab.setIcon(screens[3].drawableRes).text = "Android"
                screens[4] -> tab.setIcon(screens[4].drawableRes).text = "Science"
                screens[5] -> tab.setIcon(screens[5].drawableRes).text = "Culture"
            }
        }.attach()

    }

    private fun showDialogFragment(){
        ConfirmationDialogFragment()

            .show(supportFragmentManager, "confirmationTag")
    }

    private fun hideDialog() {
        supportFragmentManager.findFragmentByTag("confirmationTag")
            ?.let { it as ConfirmationDialogFragment }
            ?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    

//  tabLayout.getTabAt(1)?.orCreateBadge?.apply {
//            number = 2
//            badgeGravity = BadgeDrawable.TOP_END
//        }
//
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                tabLayout.getTabAt(position)?.removeBadge()
//
//            }
//        })
}