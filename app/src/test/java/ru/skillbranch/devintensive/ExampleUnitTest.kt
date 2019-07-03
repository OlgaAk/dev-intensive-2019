package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
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
        val user2 = User.makeUser(null)
        val user3 = User.makeUser("")
        val user4 = User.makeUser(" ")
        val user5 = User.makeUser("John")
        val (id, firstName, lastName) = user
        println(user.lastVisit?.format("HH:mm"))

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
        val user2 = user.copy(lastVisit = Date().add(2, TimeUnits.MINUTE))
        println(user2.lastVisit?.format() )
    }

    @Test
    fun test_data_mapping(){
        val user = User.makeUser("Donald Duck")
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Donald Duck")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "lalalal", type="text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "ooooooo", type="image")
        when(imgMessage){
            is TextMessage -> println("This is a text message")
            is ImageMessage -> println("This is an image message")
        }
        println(textMessage.formatMessage())
    }

    @Test
    fun test_toInitials(){
        val user = User.makeUser(" ")
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.MINUTE))
        val userview = user2.toUserView()
        userview.printMe()
    }

    @Test
    fun test_builder(){
        val user = User.Builder().id("a")
            .firstName("john")
            .lastName("doe")
            .avatar("avatar")
            .rating(1)
            .respect(2)
            .lastVisit(Date().add(-10, TimeUnits.MINUTE))
            .isOnline(false)
            .build()
    }

}
