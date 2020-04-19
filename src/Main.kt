import util.SolutionUtils
import java.time.Instant

fun main() {
    (1..10).forEach {
        val solutionUtils = SolutionUtils(it.toString())
        val graphData = solutionUtils.readGraphData()
        val dijkstra = Dijkstra(graphData.nodesCount, graphData.graph)
        val startTime = Instant.now()
        val mySolution = dijkstra.determineShortestPathsSequential()
        val finishTime = Instant.now()
        val isSolutionValid =
            solutionUtils.checkSolution(
                mySolution
            )
        println("Test $it passed: $isSolutionValid \n Algorithm ran in: ${finishTime.toEpochMilli() - startTime.toEpochMilli()}ms")
    }
}

