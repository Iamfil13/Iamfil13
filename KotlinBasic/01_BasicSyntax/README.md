# Основы Kotlin: синтаксис, условия, функции, циклы
### Основной синтаксис Kotlin
fun main(){

    val firstName: String = "Evgeniy"
    val lastName: String = "Filippov"
    var height = 180
    var weight: Float = 88.0F
    val isChild: Boolean
    isChild = height < 150 || weight < 40.0


    var info = """
        First Name: $firstName
        Last Name: $lastName
        Height: $height
        Weight: $weight
        Is child: $isChild
    """

    print(info)

    height += 10

    info = """
        First Name: $firstName
        Last Name: $lastName
        Height: $height
        Weight: $weight
        Is child: $isChild
    """
    println(info)


}