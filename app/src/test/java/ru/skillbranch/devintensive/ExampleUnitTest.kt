package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance(){
        val user = User("1")
        val user2 = User("2", "john", "wick")
        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true )

        user.printMe()
        user2.printMe()
        user3.printMe()
    }

    @Test
    fun test_factory(){
        val user = User.makeUser("Olia Ivanova")
        print(user)
       val user2 = user.copy(id="1")
        print(user2)

    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
        val (id, firstName, lastName) = user
        println("$id $firstName $lastName")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("Olia Ivanova")
        val user2 = user.copy(lastVisit = Date())
        println(user2.lastVisit?.format() )
    }

    @Test
    fun test_date(){
        val user = User.makeUser("Vova Ivanov")
        val user2 = user.copy(lastVisit = Date().add(2, "day"))
        println(user2.lastVisit?.format() )
    }
}
