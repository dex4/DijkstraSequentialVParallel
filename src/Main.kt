import util.SolutionUtils

fun main() {
    (1..10).forEach {
        println("Test $it")
        val solutionUtils = SolutionUtils(it.toString())
        val graphData = solutionUtils.readGraphData()
        val dijkstra = Dijkstra(graphData.nodesCount, graphData.graph)
        val mySolution = dijkstra.determineShortestPathsSequential()
        println(
            solutionUtils.checkSolution(
                mySolution
            )
        )
    }
}

