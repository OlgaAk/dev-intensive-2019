package ru.skillbranch.devintensive.extensions

fun String.truncate(maxdigits: Int=16) : String {
    var digits = maxdigits
    var ending = "..."
    var regularexpr = Regex("[\\s{1,}]$")
    var newString = this.trim()
    if(maxdigits < newString.count()) {
        newString = this.substring(0, digits).trim() + ending
    }

/*    println(regularexpr.containsMatchIn(newString))*/
    return newString
}


fun String.stripHtml() : String {
    var regularexpr = Regex("(<[^>]*>)|(&[^>]*;)")
    var regularexpr2 = Regex("\\s{2,}")

    return this.replace(regularexpr, "").replace(regularexpr2, " ")
}