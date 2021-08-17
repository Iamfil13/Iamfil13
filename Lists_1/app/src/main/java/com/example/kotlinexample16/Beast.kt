package com.example.kotlinexample16

sealed class Beast {

    data class Animals(
        val name: String,
        val avatarLink: String,
        val age: Int
    ) : Beast()

    data class Predator(
        val name: String,
        val avatarLink: String,
        val age: Int,
        val weapon: String
    ) : Beast()
}