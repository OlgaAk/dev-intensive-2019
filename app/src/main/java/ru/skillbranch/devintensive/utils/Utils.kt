package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if(fullName == null || fullName == "" || fullName ==" "){
            firstName= null
            lastName = null
        }

        return firstName to lastName
    }

    fun transliteration(payload: String, divider : String = " "): String {
        TODO()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstLetter : Char? = firstName?.getOrNull(0)?.toUpperCase()
        var secondLetter : Char? = lastName?.getOrNull(0)?.toUpperCase()

        return "$firstLetter$secondLetter"
    }


}