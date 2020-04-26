package rmi;

import kotlin.Pair;
import kotlin.Triple;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface RMIInterface extends Remote {

    public Pair<HashMap<Integer, List<Pair<Integer, Integer>>>, Triple<Integer, Integer, Integer>> helloTo(String name) throws RemoteException;

    public void returnDistances(Pair<Long,List<Integer>> distances) throws RemoteException;
}