# DijkstraSequentialVParallel

<p>All input and solution files are generated by the awesome people at <a href="https://www.infoarena.ro/">Infoarena</a>, specifially from the <a href="https://www.infoarena.ro/problema/dijkstra"> Dijkstra Educational Problem</a>.</p>
<p>Each run is verified by comparing the method result with the solution file.</p>
<p>The sequential Kotlin solution is based on one of my <a href="https://www.infoarena.ro/job_detail/1519825?action=view-source">old implementations in C++</a> evaluated on Infoarena as well.<p>

<h3> Sequential solution complexity analysis </h3>

<p>Our queue is a <b>PriorityQueue</b> which is using a heap for storing the sorted nodes based<br>
on the edge weight.</p>
<p>Such, for queue.add(node) and queue.remove() (getting the first element in the queue)<br>
we have a time complexity of O(logN) where N is the number of elements currently in the queue.<br></p>
<p>To update the minimum distance for a single edge will be O(logN) (from queue.add) and we have<br>
a total of M edges -> O(MlogN) to update all edges.</p>
<p>Finally since at each step we retrieve a node (with queue.remove) we have O(logN) for that<br>
and there areN nodes -> O(NlogN).</p>

<p>Putting these operations together will result in a total complexity of O(NlogN + MlogN) = O((N+M)logN).</p>

```
fun determineShortestPathsSequential(): List<Int> {
    val queue = initQueue()
    ...
    queue.add(Edge(STARTING_NODE, STARTING_NODE, 0))
    while (queue.isNotEmpty()) {
            node = queue.remove().nodeB
            graph[node]?.let {
                (0 until it.size).forEach { position ->
                    ...
                    if (distances[currentVertexInPath] > distances[node] + edgeWeight) {
                        ...
                        queue.add(Edge(node, currentVertexInPath, distances[currentVertexInPath]))
                    }
                }
            }
        }
}
```

<h3>Execution table for the sequential implementation</h3>

<table style="width:100%">
  <tr>
    <th>Test No.</th>
    <th>Execution Duration</th>
    <th>Nodes Count</th>
    <th>Edges Count</th>
  </tr>
  <tr>
    <td>1</td>
    <td>5ms</td>
    <td>7</td>
    <td>8</td>
  </tr>
  <tr>
    <td>2</td>
    <td>>1ms</td>
    <td>8</td>
    <td>7</td>
  </tr>
  <tr>
    <td>3</td>
    <td>5ms</td>
    <td>712</td>
    <td>1621</td>
  </tr>
  <tr>
    <td>4</td>
    <td>3ms</td>
    <td>1000</td>
    <td>2451</td>
  </tr>
  <tr>
    <td>5</td>
    <td>49m</td>
    <td>10421</td>
    <td>21313</td>
  </tr>
  <tr>
    <td>6</td>
    <td>59ms</td>
    <td>16421</td>
    <td>41012</td>
  </tr>
  <tr>
    <td>7</td>
    <td>34ms</td>
    <td>30193</td>
    <td>51201</td>
  </tr>
  <tr>
    <td>8</td>
    <td>31472ms</td>
    <td>50000</td>
    <td>140006</td>
  </tr>
  <tr>
    <td>9</td>
    <td>44ms</td>
    <td>50000</td>
    <td>74899</td>
  </tr>
  <tr>
    <td>10</td>
    <td>130ms</td>
    <td>50000</td>
    <td>249999</td>
  </tr>
</table>
