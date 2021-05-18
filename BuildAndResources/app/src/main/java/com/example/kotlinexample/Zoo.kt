package com.example.kotlinexample

import kotlin.random.Random

class Zoo {

    private val animals: MutableList<Animal> = mutableListOf()

    fun addAnimals(amount: Int, animal: Animal){
        var currentNumber = 0
        while (currentNumber < amount){
            animals.add(animal)
            currentNumber++
        }
    }

    fun printZoo() {
        println("Всего животных: ${animals.size}, а именно:")
        for (animal in animals){
            println(animal.name)
        }
    }

    fun liveZoo(n: Int) {

        var count = 0

        if (animals.isNotEmpty()) {
            val aliveAnimals: MutableList<Animal> = mutableListOf()
            val deadAnimals: MutableList<Animal> = mutableListOf()

            while (count < n) {
                animals.forEach {
                    if (!it.isTooOld){
                        when(Random.nextInt(5)){
                            0 -> it.move()
                            1 -> it.eat()
                            2 -> it.sleep()
                            3 -> aliveAnimals.add(it.makeChild())
                            4 -> {
                               if(it is Soundable){
                                   it.makeSound()
                               } else it.eat()
                            }
                        }
                    } else {
                        deadAnimals.add(it)
                    }
                }
                count++
            }
            animals.addAll(aliveAnimals)
            animals.removeAll(deadAnimals)
            printZoo()
        } else {
            println("В зоопарке нет животных")
        }
    }
}
