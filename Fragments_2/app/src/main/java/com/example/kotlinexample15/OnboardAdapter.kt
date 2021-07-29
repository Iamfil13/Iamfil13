package com.example.kotlinexample15

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardAdapter(
    private val screens: List<OnboardScreen>,

    activity: FragmentActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: OnboardScreen = screens[position]
        return OnboardFragment.newInstance(

            textRes = screen.textRes,
            bgColorRes = screen.bgColorRes,
            drawableRes = screen.drawableRes,
            tag = screen.tag
        )
    }
}