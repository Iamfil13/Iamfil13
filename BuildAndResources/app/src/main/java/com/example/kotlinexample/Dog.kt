package com.example.kotlinexample

class Dog (name: String) : Animal(name), Soundable{

    override val maxAge: Int = 20

    @Override
    override fun move() {
        super.move()
        println("$name бежит")
    }

    @Override
    override fun makeChild(): Dog {
        super.makeChild()
        return Dog(name)
    }

    override fun makeSound() {
        println("Гав-гав")
    }

}