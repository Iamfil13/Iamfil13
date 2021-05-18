package com.example.kotlinexample

class Fish (name: String) : Animal(name) {

    override val maxAge: Int = 5

    @Override
    override fun move() {
        super.move()
        println("$name плывет")
    }

    @Override
    override fun makeChild(): Fish {
        super.makeChild()
        return Fish(name)
    }
}