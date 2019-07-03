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

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date:Date = Date()) : String {
    var time = this.time
    var timeNow = Date().time
    var diff = timeNow - time
    var pastOrFutureWord : String = "назад"
    var firstInclinationNumbers = listOf<Long>(1L, 21L)
    var secondInclinationNumbers = listOf<Long>(2L, 3L, 4L, 22L, 23L, 24L)
    println("$diff,${Math.abs(diff)/ MINUTE}")
    var positiveDiff : Long = Math.abs(diff)+5* SECOND
    var string :String = when(positiveDiff) {
        in 0..1*SECOND -> "только что"
        in 1..45*SECOND  -> "${ if(diff<0) "через" else ""} несколько секунд ${ if(diff>0) "назад" else ""}"
       in 45*SECOND..75*SECOND  -> "${ if(diff<0) "через" else ""} минуту ${ if(diff>0) "назад" else ""}"
       in 75*SECOND..45*MINUTE  -> "${ if(diff<0) "через" else ""} ${positiveDiff/ MINUTE} ${if(positiveDiff/ MINUTE  in firstInclinationNumbers) "минуту" else if(positiveDiff/ MINUTE in secondInclinationNumbers) "минуты" else "минут"} ${ if(diff>0) "назад" else ""}"
       in 45*MINUTE..75*MINUTE  -> "${ if(diff<0) "через" else ""} час ${ if(diff>0) "назад" else ""}"
       in 75*MINUTE..22* HOUR -> "${ if(diff<0) "через" else ""} ${positiveDiff/ HOUR} ${if(positiveDiff/ HOUR in firstInclinationNumbers) "час" else if(positiveDiff/ HOUR in secondInclinationNumbers) "часа" else "часов"} ${ if(diff>0) "назад" else ""}"
       in 22* HOUR..26* HOUR -> "${ if(diff<0) "через" else ""} день ${ if(diff>0) "назад" else ""}"
       in 26* HOUR..360* DAY -> "${ if(diff<0) "через" else ""} ${positiveDiff/ DAY} ${if(positiveDiff/DAY in firstInclinationNumbers) "день" else if(positiveDiff/DAY in secondInclinationNumbers) "дня" else "дней"} ${ if(diff>0) "назад" else ""}"
       else -> "${if(diff<0) "более чем через год" else "более года назад"}"
    }
    return string
}



