package model

data class GraphData(
    val nodesCount: Int,
    val edgesCount: Int,
    val graph: HashMap<Int, MutableList<Pair<Int, Int>>>
)