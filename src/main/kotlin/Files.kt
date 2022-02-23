import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.function.BiConsumer


@SafeVarargs
fun readAndDo(filename: String, vararg actions: BiConsumer<List<String>, List<String>>) {

    BufferedReader(FileReader("src/main/resources/$filename")).use { file ->
        val customerCount = file.readLine().toInt()

        for (i in 0 until customerCount) {

            val likesStr = file.readLine().split(' ')
            val likes = likesStr.subList(1, likesStr.size)

            val dislikesStr = file.readLine().split(' ')
            val dislikes = dislikesStr.subList(1, dislikesStr.size)

            for (action in actions) {
                action.accept(likes, dislikes)
            }
        }
    }
}

fun readAllCustomers(filename: String): List<Customer> {

    val ingredientCache = HashMap<String, String>()
    val cacheGet: (String) -> String = { ingredient: String ->
        ingredientCache.putIfAbsent(ingredient, ingredient)
        ingredientCache.getValue(ingredient)
    }

    val customers = ArrayList<Customer>(1025)
    var customerId = 0L

    readAndDo(filename, { i, j ->
        val likes = i.map(cacheGet)
        val dislikes = j.map(cacheGet)
        customers.add(Customer(customerId++, likes, dislikes))
    })
    return customers
}

fun writeAnswer(answer: String, outputName: String) {
    val outputDir: String = try {
        File("src/main/resources/outputFileName.txt").readLines()[0]
    } catch (e: FileNotFoundException) {
        ""
    }

    File("$outputDir/$outputName").writeText("$answer\n")
}
