import java.io.BufferedReader
import java.io.File
import java.io.FileReader

const val outputDir = "/home/abductcows/Desktop/"

fun read(filename: String): List<Customer> {

    val ingredientCache = mutableMapOf<String, String>()
    val cacheGet: (String) -> String = { ingredient: String ->
        ingredientCache.putIfAbsent(ingredient, ingredient)
        ingredientCache[ingredient]!!
    }

    lateinit var customers: MutableList<Customer>
    var customerId = 0L

    BufferedReader(FileReader("src/main/resources/$filename")).use { file ->
        val customerCount = file.readLine().toInt()
        customers = ArrayList(customerCount)

        for (i in 0 until customerCount) {

            val likesStr = file.readLine().split(' ')
            val likes = likesStr.subList(1, likesStr.size).map(cacheGet)

            val dislikesStr = file.readLine().split(' ')
            val dislikes = dislikesStr.subList(1, dislikesStr.size).map(cacheGet)

            customers.add(Customer(customerId++, likes, dislikes))
        }
    }

    return customers
}

fun writeAnswer(answer: String, qName: String) = File("$outputDir$qName").writeText("$answer\n")
