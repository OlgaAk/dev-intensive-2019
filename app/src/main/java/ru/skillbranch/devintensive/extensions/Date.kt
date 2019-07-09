package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.util.*

const val SECOND =  1000L
const val MINUTE =  60 * SECOND
const val HOUR =  60 * MINUTE
const val DAY =  24 * HOUR


fun Date.format(pattern: String="HH:mm:ss dd.MM.yy") : String {
    val dateFormat = java.text.SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE ->   value * MINUTE
        TimeUnits.HOUR  -> value * HOUR
        TimeUnits.DAY   -> value * DAY
    }
    this.time = time
    return this
}

fun declineHelper(number: Long, arr: Array<String>) : String{
    var firstInclinationNumbers = listOf<Long>(1L)
    var secondInclinationNumbers = listOf<Long>(2L, 3L, 4L)
    if(number%10  in firstInclinationNumbers && number%100 != 11L){
        return "$number ${arr.get(0)}"
    } else if(number%10 in secondInclinationNumbers && number%10 != 12L) {
        return "$number ${arr.get(1)}"
    } else {
        return "$number ${arr.get(2)}"
    }
}

enum class TimeUnits{
    SECOND {
        override fun plural(number: Long) : String{
            var arr = arrayOf("секунду", "секунды", "секунд")
            return declineHelper(number, arr)
        }
    },
    MINUTE {
        override fun plural(number: Long): String {
            var arr = arrayOf("минуту", "минуты", "минут")
            return declineHelper(number, arr)
        }
    },
    HOUR {
        override fun plural(number: Long): String {
            var arr = arrayOf("час", "часа", "часов")
            return declineHelper(number, arr)
        }
    },
    DAY {
        override fun plural(number: Long): String {
            var arr = arrayOf("день", "дня", "дней")
            return declineHelper(number, arr)
        }
    };

    abstract fun plural(number: Long) : String


}

fun Date.humanizeDiff(date:Date = Date()) : String {
    var time = this.time
    var timeNow = date.time
    var diff = timeNow - time
    var pastOrFutureWord : String = "назад"
    var firstInclinationNumbers = listOf<Long>(1L, 21L)
    var secondInclinationNumbers = listOf<Long>(2L, 3L, 4L, 22L, 23L, 24L)
    var positiveDiff : Long = Math.abs(diff)
    var string :String = when(positiveDiff) {
        in 0..1*SECOND -> "только что"
        in 1..45*SECOND  -> "${ if(diff<0) "через " else ""}несколько секунд${ if(diff>0) " назад" else ""}"
       in 45*SECOND..75*SECOND  -> "${ if(diff<0) "через " else ""}минуту ${ if(diff>0) "назад" else ""}"
       in 75*SECOND..45*MINUTE  -> "${ if(diff<0) "через " else ""}${positiveDiff/ MINUTE} ${if(positiveDiff/ MINUTE  in firstInclinationNumbers) "минуту" else if(positiveDiff/ MINUTE in secondInclinationNumbers) "минуты" else "минут"}${ if(diff>0) " назад" else ""}"
       in 45*MINUTE..75*MINUTE  -> "${ if(diff<0) "через " else ""}час ${ if(diff>0) "назад" else ""}"
       in 75*MINUTE..22* HOUR -> "${ if(diff<0) "через " else ""}${positiveDiff/ HOUR} ${if(positiveDiff/ HOUR in firstInclinationNumbers) "час" else if(positiveDiff/ HOUR in secondInclinationNumbers) "часа" else "часов"}${ if(diff>0) " назад" else ""}"
       in 22* HOUR..26* HOUR -> "${ if(diff<0) "через " else ""}день ${ if(diff>0) "назад" else ""}"
       in 26* HOUR..360* DAY -> "${ if(diff<0) "через " else ""}${positiveDiff/ DAY} ${if(positiveDiff/DAY in firstInclinationNumbers) "день" else if(positiveDiff/DAY in secondInclinationNumbers) "дня" else "дней"}${ if(diff>0) " назад" else ""}"
       else -> "${if(diff<0) "более чем через год" else "более года назад"}"
    }
    println(string)
    return string
}



