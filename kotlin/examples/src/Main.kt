/**
 * @author seongheum.park
 */

fun main(args: Array<String>) {
    val main = Main()
    main.start()
}

class Main {
    var person: Person? = null

    init {
        person = Person("Bill", 20)
        println(person)
    }

    fun start() {
        person?.apply {
            name = "Jake"
            age = 28
        }
        println(person)
    }
}