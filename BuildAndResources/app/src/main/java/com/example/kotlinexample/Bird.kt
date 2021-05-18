package com.example.kotlinexample

class Bird(name: String) : Animal(name), Soundable {

    override val maxAge: Int = 10

    @Override
    override fun move() {
        super.move()
        println("$name летит")
    }

    @Override
    override fun makeChild(): Bird {
        super.makeChild()
        return Bird(name)
    }

    override fun makeSound() {
        println("Чирик-чирик")
    }

}