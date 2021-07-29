package com.example.kotlinexample15

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnClickPositiveButton {

    private var screens: List<OnboardScreen> = listOf(

        OnboardScreen(
            textRes = R.string.business,
            bgColorRes = android.R.color.holo_orange_dark,
            drawableRes = R.drawable.ic_money,
            tag = "Business"
        ),
        OnboardScreen(
            textRes = R.string.sport,
            bgColorRes = android.R.color.holo_green_light,
            drawableRes = R.drawable.ic_sports,
            tag = "Sports"
        ),
        OnboardScreen(
            textRes = R.string.medicine,
            bgColorRes = R.color.teal_200,
            drawableRes = R.drawable.ic_medical,
            tag = "Medicine"
        ),
        OnboardScreen(
            textRes = R.string.android,
            bgColorRes = android.R.color.holo_orange_light,
            drawableRes = R.drawable.ic_android,
            tag = "Android"
        ),
        OnboardScreen(
            textRes = R.string.science,
            bgColorRes = android.R.color.holo_purple,
            drawableRes = R.drawable.ic_science,
            tag = "Science"
        ),
        OnboardScreen(
            textRes = R.string.culture,
            bgColorRes = android.R.color.holo_red_light,
            drawableRes = R.drawable.ic_deck,
            tag = "Culture"
        )

    )

    private lateinit var selectType: Button
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: OnboardAdapter
    private lateinit var tabLayout: TabLayout


    private var booleanScreen: BooleanArray = booleanArrayOf(true, true, true, true, true, true)

    private var booleanScreenFromDialog: BooleanArray =
        booleanArrayOf(true, true, true, true, true, true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState !== null) {
            val get = savedInstanceState.getBooleanArray(KEY_SCREENS) ?: error("Error")
            onClickSelect(get)
            selectType = findViewById(R.id.showDialog)
            selectType.setOnClickListener {
                ConfirmationDialogFragment.newInstance(booleanScreenFromDialog)
                    .show(supportFragmentManager, KEY_SCREENS)
            }
        } else {
            onClickSelect(booleanScreen)
            selectType = findViewById(R.id.showDialog)
            selectType.setOnClickListener {
                ConfirmationDialogFragment.newInstance(booleanScreenFromDialog)
                    .show(supportFragmentManager, KEY_SCREENS)
            }
        }

    }


    override fun onClickSelect(checkedItems: BooleanArray) {
        val newScreens: MutableList<OnboardScreen> = mutableListOf()
        val tags = arrayOf("Business", "Sports", "Medicine", "Android", "Science", "Culture")
        val checkedFilterTags: MutableList<String> = mutableListOf()

        booleanScreenFromDialog = checkedItems

        for (i in tags.indices) {
            val isChecked = checkedItems[i]
            if (isChecked) {
                checkedFilterTags.add(tags[i])
            }
        }
        filter(screens, checkedFilterTags, newScreens)
        showTabLayout(newScreens)
    }

    private fun filter(
        screens: List<OnboardScreen>,
        checkedFilterTags: MutableList<String>,
        newScreens: MutableList<OnboardScreen>
    ) {
        for (i in screens) {
            if (checkedFilterTags.contains(i.tag.replace("[", "").replace("]", ""))) {
                newScreens.add(i)
            }
        }
    }


    private fun showTabLayout(newScreen: List<OnboardScreen>) {

        adapter = OnboardAdapter(newScreen, this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        tabLayout = findViewById(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.setIcon(newScreen[position].drawableRes).text =
                newScreen[position].tag.replace("[", "").replace("]", "")
        }.attach()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(KEY_SCREENS, booleanScreenFromDialog)
    }

    companion object {
        internal const val KEY_SCREENS = "checked items key"
    }


}