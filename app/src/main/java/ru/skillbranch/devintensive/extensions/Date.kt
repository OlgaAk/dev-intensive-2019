package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.util.*
import java.util.regex.Pattern

const val SECOND =  1000
const val MINUTE =  60 * SECOND
const val HOUR =  60 * MINUTE
const val DAY =  24 * HOUR


fun Date.format(pattern: String="HH:mm:ss dd.MM.yy") : String {
    val dateFormat = java.text.SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: String) : Date {
    var time = this.time
    time += when(units){
        "second" -> value * SECOND
        "minute" ->   value * MINUTE
        "hour"  -> value * HOUR
        "day"   -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}