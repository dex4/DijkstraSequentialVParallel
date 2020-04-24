import util.Dijkstra
import util.SolutionUtils
import util.trimResultNoAddition
import java.time.Instant

fun main() {
//    (1..10).forEach {
//        val solutionUtils = SolutionUtils(it.toString())
//        val graphData = solutionUtils.readGraphData()
//        val dijkstra = util.Dijkstra(graphData.nodesCount, graphData.graph)
//        val startTime = Instant.now()
//        val mySolution = dijkstra.determineShortestPathsSequential()
//        val finishTime = Instant.now()
//        val isSolutionValid =
//            solutionUtils.checkSolution(
//                mySolution
//            )
//        println("Test $it passed: $isSolutionValid \n Algorithm ran in: ${finishTime.toEpochMilli() - startTime.toEpochMilli()}ms")
//    }
    (1..6).forEach {
        val solutionUtils = SolutionUtils(it.toString())
        val graphData = solutionUtils.readGraphData()
        val dijkstra = Dijkstra(graphData.nodesCount, graphData.graph)
        val distances = mutableListOf<Int>()
        val startTime = Instant.now()
        (2..graphData.nodesCount).forEach {node ->
            distances.add(dijkstra.determineShortestPath(node))
        }
        val finishTime = Instant.now()
        val mySolution = trimResultNoAddition(distances)
        println(distances)
        println(mySolution)
        val isSolutionValid =
            solutionUtils.checkSolution(
                mySolution
            )
        println("Test $it passed: $isSolutionValid \n Algorithm ran in: ${finishTime.toEpochMilli() - startTime.toEpochMilli()}ms")
    }
}
