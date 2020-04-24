package rmi;

import kotlin.Pair;
import kotlin.Triple;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;
    private static util.SolutionUtils solutionUtils = new util.SolutionUtils("1");
    private static model.GraphData graphData = solutionUtils.readGraphData();

    protected ServerOperation() throws RemoteException {
        super();
    }

    @Override
    public Pair<HashMap<Integer, List<Pair<Integer, Integer>>>, Triple<Integer, Integer, Integer>> helloTo(String name) throws RemoteException {
        return new Pair<>(graphData.getGraph(), new Triple<>(2, 4, graphData.getNodesCount()));
    }

    @Override
    public void returnDistances(List<Integer> distances) throws RemoteException {
        System.out.println(distances);
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