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
        val lettersMap  = mapOf<Char, String>('а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e",'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l",
            'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t",
            'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'", 'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya")
        var transliteratedName: String = ""
        for(index in payload.indices){
            var letter:Char = payload[index]
           if ( letter in lettersMap){
               transliteratedName+=lettersMap[letter]
           } else if (letter.toLowerCase() in lettersMap){
               transliteratedName+= lettersMap[letter.toLowerCase()]?.toUpperCase()
           }
            else {
               transliteratedName+= payload[index]
           }
        }
        var list : List<String> = transliteratedName.split(" ")
        transliteratedName = list.joinToString(divider)
         return transliteratedName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstLetter : Char? = firstName?.getOrNull(0)?.toUpperCase()
        var secondLetter : Char? = lastName?.getOrNull(0)?.toUpperCase()
        if(firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return null
        }
        if(firstName.isNullOrBlank() && !lastName.isNullOrBlank()){
                return  secondLetter.toString()
            } else if (!firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return firstLetter.toString()
        }

        return "$firstLetter$secondLetter"
    }


}