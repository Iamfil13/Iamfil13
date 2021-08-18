package com.example.kotlinexample16

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class Beast {

    @Parcelize
    data class Animals(
        val name: String,
        val avatarLink: String,
        val age: Int
    ) : Beast(), Parcelable

    @Parcelize
    data class Predator(
        val name: String,
        val avatarLink: String,
        val age: Int,
        val weapon: String
    ) : Beast(), Parcelable
}