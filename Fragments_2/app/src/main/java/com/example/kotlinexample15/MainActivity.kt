package com.example.kotlinexample15

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

    private val selectScreen = arrayOf(
        "Business",
        "Sports",
        "Medicine",
        "Android",
        "Science",
        "Culture"
    )

    private var selectBooleanScreen: BooleanArray = booleanArrayOf(
        true,
        true,
        true,
        true,
        true,
        true,
    )

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            selectBooleanScreen = savedInstanceState.getBooleanArray(KEY_SCREENS) ?: error("Error")
        }

        showTabLayout()

        findViewById<Button>(R.id.showDialog).setOnClickListener {
            showDialogFragment()
        }

    }


    private fun showTabLayout() {
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = OnboardAdapter(screens, this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (selectBooleanScreen[position]) {
                when (screens[position]) {
                    screens[position] ->
                        tab.setIcon(screens[position].drawableRes).text =
                            selectScreen[position]
                }
            }
        }.attach()
    }


    private fun showDialogFragment() {

        AlertDialog.Builder(this)
            .setTitle("Article type")
            .setMultiChoiceItems(
                selectScreen, selectBooleanScreen
            ) { _, which, isChecked ->
                selectBooleanScreen[which] = isChecked
            }
            .setPositiveButton("Select") { _, _ ->
                showTabLayout()
            }
            .show()

//        ConfirmationDialogFragment()
//            .show(supportFragmentManager, "confirmationTag")

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

    companion object {
        internal const val KEY_SCREENS = "counter"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(KEY_SCREENS, selectBooleanScreen)
    }


}