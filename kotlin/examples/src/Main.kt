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

    private fun <T> copy(dest: MutableList<T>, src: List<T>) {
        for (item: T in src) {
            dest.add(item)
        }
    }

    val FILL_THRESHOLD = 10

    private fun <T> fill(list: MutableList<in T>, obj: T) {
        val size = list.size

        if (size < FILL_THRESHOLD || list is RandomAccess) {
            for (i in 0 until size) {
                list[i] = obj
            }
        } else {
            val itr = list.listIterator()
            for (i in 0 until size) {
                itr.next()
                itr.set(obj)
            }
        }
    }
}