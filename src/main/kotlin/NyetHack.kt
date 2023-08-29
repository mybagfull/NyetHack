var heroName: String = ""

fun main() {
    promptHeroName()
    //changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)}, reads to the town square")
    visitTavern()
}

private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt. What is their name?",
        ::makeYellow
    )
    /*val heroName = readLine()
    require(heroName !=null && heroName.isNotEmpty()) {
        "The hero must have a name."
    }*/
    heroName = "Madrigal"
    println(heroName)
    return heroName
}

private fun makeYellow(message: String) = "\u001B[33;1m$message\u001B[0m"

private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit()} -> "The identifiable"
        name.none { it.isLetter()} -> "The Witness Protection Member"
        name.lowercase() == name.lowercase().reversed() -> "///Palindromist///"
        name.count { it.isLetterOrDigit()}  > 21 -> "S P A C I O U S"
        name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowel"
        name.all { it.isUpperCase()} -> "Eminent"
        else -> "The Renowned Hero"
    }
}
