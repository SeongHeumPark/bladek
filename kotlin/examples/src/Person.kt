/**
 * @author seongheum.park
 */
class Person {
    var name: String
    var age: Int

    constructor() {
        name = "no name"
        age = 0
    }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "name is $name, $age years old"
    }
}