import java.io.File

internal object Resources

fun main(args: Array<String>) {

    val legalPair = mapOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
    val errorScore = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

    val score = mapOf<Char, Long>(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )
    var errorScoreSum = 0
    var lineScore: Long = 0

    val chunkList = mutableListOf<Char>()
    val scores = mutableListOf<Long>()

    val inputText: List<String> = File(Resources.javaClass.classLoader.getResource("input_text.txt").toURI())
        .readLines()



    fun getErrorSum(): Int {
        inputText.forEach { input ->
            println(input)
            input.forEach { value ->
                if (value in legalPair.keys) {
                    chunkList.add(value)
                } else {
                    if (legalPair[chunkList.removeLast()] != value) {
                        errorScoreSum += errorScore[value]!!
                    }
                }
            }
        }

        return errorScoreSum
    }

   fun getMiddleScore(): Long {
       val flipList = mutableListOf<Char>()
       chunkList.forEach { ch_ ->
           when(ch_){
               '(' -> {
                   flipList.add(')')
               }
               '[' -> {
                   flipList.add(']')
               }
               '{' -> {
                   flipList.add('}')
               }
               '<' -> {
                   flipList.add('>')
               }
           }
       }

       flipList.forEach { _ch ->
           lineScore = lineScore * 5 + score[_ch]!!
           scores.add(lineScore)
       }
       val midScore = scores[scores.size / 2]

       return midScore
   }


    println("midScore ::: ${getMiddleScore()}")
    println("errorSum ::: ${getErrorSum()}")
}
