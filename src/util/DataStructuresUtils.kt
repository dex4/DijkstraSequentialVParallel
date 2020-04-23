package util

import model.Edge
import java.util.*
import kotlin.Comparator

fun trimResult(finalDistances: MutableList<Int>): List<Int> =
    finalDistances.apply {
//        finalDistances.removeAt(0)
//        finalDistances.removeAt(0)
        finalDistances.forEachIndexed { index, distance ->
            if (distance == Int.MAX_VALUE) {
                finalDistances[index] = 0
            }
        }
    }

fun trimResultNoAddition(finalDistances: MutableList<Int>): List<Int> =
    finalDistances.apply {
        finalDistances.forEachIndexed { index, distance ->
            if (distance == Int.MAX_VALUE) {
                finalDistances[index] = 0
            }
        }
    }

fun initDistances(nodesCount: Int): MutableList<Int> {
    return mutableListOf<Int>().apply {
        add(-1)
        add(0)
        (1 until nodesCount).forEach { _ ->
            add(Int.MAX_VALUE)
        }
    }
}

fun initQueue() =
    PriorityQueue(Comparator<Edge> { edge1, edge2 ->
        edge1.cost - edge2.cost
    })