import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Denchik", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) {index ->
    val (_, name, _) = menuData[index].split(",")
    name
}
private val menuPrices = List(menuData.size) {index ->
    val (_, _, price) = menuData[index].split(",")
    price
}
private val menuTypes = List(menuData.size) {index ->
    val (type, _, _) = menuData[index].split(",")
    type
}


fun visitTavern() {
narrate("$heroName enters $TAVERN_NAME")
    menuOpen()
    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.5
    )
    while(patrons.size < 10) {
        patrons += "${firstNames.random()} ${lastNames.random()}"
    }
    println(patronGold)
    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())
    repeat(3) {
        placeOrder(patrons.random(), menuItems.random())
    }

}

private fun menuOpen() {
    println("*** Welcome to $TAVERN_MASTER's Folly ***")
    var i = 0
    var type = 0
    val currentType = menuTypes.distinct()
    while(type < currentType.size - 1) {
        println(" ".repeat(30 / 2 - (currentType[type].length / 2)) + "~[" + currentType[type] + "]~")
        while (i < menuItems.size - 1) {
            if (menuTypes[i] == currentType[type]) {
                println(menuItems[i] + ".".repeat(34 - menuItems[i].length - menuPrices[i].length) + menuPrices[i])
            }
            i++
        }
        type++
        i = 0
    }

}
private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}
