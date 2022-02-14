import java.io.File

internal object Resources

fun main(args: Array<String>) {


    var errorScoreSum = 0

    val inputText: List<String> = File(Resources.javaClass.classLoader.getResource("input_text.txt").toURI())
        .readLines()
    println(inputText)

    val legalPair = mapOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
    println(legalPair.entries)


    val errorScore = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)


    println(errorScore.entries)

    var lineScore: Long = 0

    val score = mapOf<Char, Long>(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    val stackList = mutableListOf<Char>()
    val scores = mutableListOf<Long>()

    inputText.forEach { input ->
        println(input)
            input.forEach { value ->
                if (value in legalPair.keys) {
                    stackList.add(value)
                } else {
                    if (legalPair[stackList.removeLast()] != value) {
                        errorScoreSum += errorScore[value]!!
                    }
                }
        }
    }

    val flipList = mutableListOf<Char>()
    stackList.forEach { ch_ ->
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
    println("midScore ::: ${midScore}")
    println("scores ::: ${scores}")
    println("flipList ::: ${flipList}")
    println("lineScore ::: ${lineScore/2}")

    println("FlipList size :: ${flipList.size}")
    println("stackList :: ${stackList}")
    println("stackList.size :: ${stackList.size}")
    println("errorSum ::: $errorScoreSum")
}
