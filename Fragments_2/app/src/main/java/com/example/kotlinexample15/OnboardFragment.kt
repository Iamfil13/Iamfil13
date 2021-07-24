package com.example.kotlinexample15

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

class OnboardFragment: Fragment (R.layout.fragment_onboarding) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))

        requireView().findViewById<TextView>(R.id.textView)
            .setText(requireArguments().getInt(KEY_TEXT))

        requireView().findViewById<ImageView>(R.id.imageView)
            .setImageResource(requireArguments().getInt(KEY_IMAGE))

    }

    companion object {

        private const val KEY_TEXT = "text"
        private const val KEY_COLOR = "color"
        private const val KEY_IMAGE = "image"

        fun newInstance(
            @StringRes textRes: Int,
            @ColorRes bgColorRes: Int,
            @DrawableRes drawableRes: Int,
        ): OnboardFragment {
            return OnboardFragment().withArguments{

                putInt(KEY_TEXT, textRes)
                putInt(KEY_COLOR, bgColorRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }
    }

}