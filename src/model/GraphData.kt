@file:JvmName("model.GraphData")
package model

import java.io.Serializable

data class GraphData(
    val nodesCount: Int,
    val edgesCount: Int,
    val graph: HashMap<Int, MutableList<Pair<Int, Int>>>
): Serializable