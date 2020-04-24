@file:JvmName("util.SolutionUtils")
package util

import model.GraphData
import java.io.File

class SolutionUtils(fileNumber: String) {
    private val outputFile: String = "src/solutions/grader_test$fileNumber.ok"
    private val inputFile: String = "src/input/grader_test$fileNumber.in"
    private var actualSolution: List<Int> = listOf()

    init {
        actualSolution = getSolution()
    }

    fun checkSolution(computedSolution: List<Int>): Boolean =
        actualSolution == computedSolution


    private fun getSolution(): List<Int> {
        val fileLines = File(outputFile).readLines().map { it.trim().split(" ") }.toMutableList()
        val solution = mutableListOf<Int>()
        fileLines[0].forEach {
            solution.add(it.toInt())
        }
        return solution
    }

    fun readGraphData(): GraphData {
        val fileLines = File(inputFile).readLines().map { it.split(" ") }.toMutableList()
        val nodesCount = fileLines[0][0].toInt()
        val edgesCount = fileLines[0][1].toInt()
        fileLines.removeAt(0)
        val graph = HashMap<Int, MutableList<Pair<Int, Int>>>()
        fileLines.forEach { line ->
            line.map { it.toInt() }.let {
                val nodeA = it[0]
                val nodeB = it[1]
                val cost = it[2]
                val currentEdges = graph[nodeA]?.toMutableList() ?: mutableListOf()
                currentEdges.add(Pair(nodeB, cost))
                graph[nodeA] = currentEdges
            }
        }
        return GraphData(nodesCount, edgesCount, graph)
    }
}