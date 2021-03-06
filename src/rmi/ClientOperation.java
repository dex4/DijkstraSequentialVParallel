package rmi;

import kotlin.Pair;
import kotlin.Triple;
import util.Dijkstra;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.DataStructuresUtilsKt.trimResult;

public class ClientOperation {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RMIInterface look_up = (RMIInterface) Naming.lookup("//localhost/MyServer");

        Pair<HashMap<Integer, List<Pair<Integer, Integer>>>, Triple<Integer, Integer, Integer>> workload = look_up.helloTo("txt");
        HashMap<Integer, List<Pair<Integer, Integer>>> graphData = workload.getFirst();

        int startNode = workload.getSecond().getFirst();
        int endNode = workload.getSecond().getSecond();
        int nodesCount = workload.getSecond().getThird();

        List<Integer> distances = new ArrayList<>();
        Dijkstra dijkstra = new Dijkstra(nodesCount, graphData);

        System.out.println("Computing solution for nodes in interval [" + startNode + ", " + endNode + "]");
        long start = System.currentTimeMillis();
        for (int i = startNode; i <= endNode; i++) {
            distances.add(dijkstra.determineShortestPath(i));
        }
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        look_up.returnDistances(new Pair<>(elapsed, trimResult(distances)));
    }

}