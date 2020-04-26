package rmi;

import kotlin.Pair;
import kotlin.Triple;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;
    private static util.SolutionUtils solutionUtils = new util.SolutionUtils("5");
    private static model.GraphData graphData = solutionUtils.readGraphData();
    private static ArrayList<Integer> solBuilder = new ArrayList<>();
    private static ArrayList<Long> elapsedTimeForAllClients = new ArrayList<>();
    private int count = 0;
    private int clients = 4;
    private int start = 2;
    private int end = graphData.getNodesCount() / clients;

    protected ServerOperation() throws RemoteException {
        super();
    }

    @Override
    public Pair<HashMap<Integer, List<Pair<Integer, Integer>>>, Triple<Integer, Integer, Integer>> helloTo(String name) throws RemoteException {
        Pair<HashMap<Integer, List<Pair<Integer, Integer>>>, Triple<Integer, Integer, Integer>> data =
                new Pair<>(graphData.getGraph(), new Triple<>(start, end, graphData.getNodesCount()));
        count++;
        start = end + 1;
        if (end + graphData.getNodesCount() / clients + 2 <= graphData.getNodesCount()) {
            end += graphData.getNodesCount() / clients + 2;
        } else {
            end = graphData.getNodesCount();
        }

        return data;
    }

    @Override
    public void returnDistances(Pair<Long, List<Integer>> distances) throws RemoteException {
        solBuilder.addAll(distances.getSecond());
        elapsedTimeForAllClients.add(distances.getFirst());
        if (count == clients) {
            if (solutionUtils.checkSolution(solBuilder)) {
                System.out.println("True " + solBuilder);
                OptionalLong time = elapsedTimeForAllClients.stream().mapToLong(v -> v).max();
                System.out.println("Elapsed: " + time);
            }
        }
    }


    public static void main(String[] args) {
        try {
            Naming.rebind("//localhost/MyServer", new ServerOperation());

            System.out.println(graphData.toString());
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}