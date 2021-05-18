package com.example.kotlinexample

import kotlin.random.Random


abstract class Animal constructor(

    val name: String

) : AgedAnimal() {

    init {
        println("Животное $name было создано.")
    }

    private var age = 0

    var energy: Int = 0
        private set

    var weight: Int = 0
        private set

    open val isTooOld: Boolean
        get() = age >= maxAge

    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) {
            age++
        }
    }

    fun sleep() {
        if (!isTooOld) {
            energy += 5
            age++
            println("$name спит")
        }
    }

    fun eat() {
        if (!isTooOld) {
            energy += 3
            weight++
            incrementAgeSometimes()
            println("$name ест")
        }
    }

    open fun move() {
        if (energy >= 5 && weight >= 1 && !isTooOld) {
            energy -= 5
            weight--
            incrementAgeSometimes()
            println("$name двигается")
        }
    }

    open fun makeChild(): Animal {
        energy = Random.nextInt(10) + 1
        weight = Random.nextInt(5) + 1

        println("Параметры $name: энергия: $energy, вес: $weight, макимальный возраст: $maxAge")

        return object : Animal(name) {
            override val maxAge: Int
                get() = this@Animal.maxAge
        }
    }

}


