import model.Edge
import util.initDistances
import util.initQueue
import util.trimResult

class Dijkstra(
    private val nodesCount: Int,
    private val graph: HashMap<Int, MutableList<Pair<Int, Int>>>
) {
    fun determineShortestPathsSequential(): List<Int> {
        var node: Int
        var currentVertexInPath: Int
        var edgeWeight: Int
        val queue = initQueue()
        val distances = initDistances(nodesCount)
        queue.add(Edge(STARTING_NODE, STARTING_NODE, 0))
        while (queue.isNotEmpty()) {
            node = queue.remove().nodeB
            graph[node]?.let {
                (0 until it.size).forEach { position ->
                    currentVertexInPath = it[position].first
                    edgeWeight = it[position].second
                    if (distances[currentVertexInPath] > distances[node] + edgeWeight) {
                        distances[currentVertexInPath] = distances[node] + edgeWeight
                        queue.add(Edge(node, currentVertexInPath, distances[currentVertexInPath]))
                    }
                }
            }
        }
        return trimResult(distances)
    }

    companion object {
        private const val STARTING_NODE = 1
    }
}