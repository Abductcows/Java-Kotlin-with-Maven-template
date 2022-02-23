
typealias Ingredient = String

data class Customer(val id: Long, val likes: List<Ingredient>, val dislikes: List<Ingredient>)

val clients = read("a")

fun main() {

    val lcounts = ingredientCount()
    ingredientCountDebug(lcounts)


}


fun solveStupid(clients: List<Customer>) {


}

fun calculateScore(include: List<Ingredient>): Long {

    val includeSet = include.toSet()
    val like = clients.filter { include.containsAll(it.likes) }
    val thenNotDislike = like.filter {
        for (disliked in it.dislikes) {
            if (includeSet.contains(disliked)) {
                return@filter false
            }
        }
        return@filter true
    }

    return thenNotDislike.size.toLong()
}

fun ingredientCount(): Map<Ingredient, IntArray> {

    val result = mutableMapOf<Ingredient, IntArray>()

    for (client in clients) {
        for (ingredient in client.likes) {
            result.computeIfAbsent(ingredient) { IntArray(2) { 0 } }[0]++
        }
        for (ingredient in client.dislikes) {
            result.computeIfAbsent(ingredient) { IntArray(2) { 0 } }[1]++
        }
    }

    return result
}

fun ingredientCountDebug(counts: Map<Ingredient, IntArray>) {

    counts.forEach { (ing, count) ->
        println("$ing: likes: ${count[0]}, dislikes: ${count[1]}")
    }
}










