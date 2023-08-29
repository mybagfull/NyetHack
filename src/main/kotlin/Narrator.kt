import kotlin.random.Random
import kotlin.random.nextInt
var narrationModifier: (String) -> String = {it}
inline fun narrate(
    message: String,
    modifier: (String) -> String = {narrationModifier(it) }
) {
    println(modifier(message))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String
    when (Random.nextInt(6..8)) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExclamationPoints = 3
                message.uppercase() + "!".repeat(numExclamationPoints)
            }
        }
        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "... ")
            }
        }
        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }
        4 -> {
            var narrationsGiven = 0
            mood = "like sending an itemized bill"
            modifier = { message ->
                narrationsGiven++
                "$message.\n(I have narrated $narrationsGiven things)"
            }
        }
        5 -> {
            mood = "lazy"
            modifier = { message -> message.substring(0..10)}
        }
        6 -> {
            mood = "leet"
            modifier = { message -> message.uppercase().replace(Regex("[LET]")) {
                it -> when(it.value) {
                    "L" -> "1"
                    "E" -> "3"
                    "T" -> "7"
                    else -> ""
                }
            }
            }
        }
        7 -> {
            mood = "poetic"
            modifier = { message ->
                message.replace(Regex(" ")) { it ->
                    when (it.value) {
                        " " -> {
                            when (Random.nextBoolean()) {
                                true -> "\n"
                                else -> " "
                            };
                        }
                        else -> ""
                    }
                }
            }
        }
        else -> {
            mood = "professional"
            modifier = { message ->
                "$message."
            }
        }
    }
    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}
