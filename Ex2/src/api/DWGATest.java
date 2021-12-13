package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DWGATest {
    DWG G = new DWG();

    @Test
    void isConnected() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0);
        G.connect(2, 1, 1);
        G.connect(0, 1, 2);
        G.connect(1, 0, 2);
        DWGA algo = new DWGA();
        algo.init(G);
        Assertions.assertTrue(algo.isConnected());
    }

    @Test
    void shortestPathDist() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0);
        G.connect(2, 0, 1);
        G.connect(1, 0, 2);
        DWGA algo = new DWGA();
        algo.init(G);
        Assertions.assertEquals(algo.shortestPathDist(1, 0), 1);
    }

    @Test
    void shortestPath() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0);
        G.connect(2, 0, 1);
        G.connect(1, 0, 2);
        DWGA algo = new DWGA();
        algo.init(G);
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1);
        test.add(2);
        test.add(0);
        List<NodeData> ARR = algo.shortestPath(1, 0);
        for (int i = 0; i < ARR.size(); i++) {
            Assertions.assertEquals(ARR.get(i).getKey(), test.get(i));
        }
    }

    @Test
    void center() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0);
        G.connect(2, 0, 1);
        G.connect(1, 0, 2);
        G.connect(0, 1, 2);

        DWGA algo = new DWGA();
        algo.init(G);
        Assertions.assertEquals(algo.center().getKey(), 1);
    }

    @Test
    void tsp() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0.2);
        G.connect(2, 1, 1);
        G.connect(1, 0, 0.1);
        G.connect(0, 1, 8);
        G.connect(2, 0, 5);
        G.connect(0, 2, 6);



        List<NodeData> cities = new ArrayList<NodeData>();
        cities.add(v.get(1));
        cities.add(v.get(0));
        DWGA algo = new DWGA();
        algo.init(G);
        List<NodeData> test = algo.tsp(cities);
        Assertions.assertTrue(algo.shortestPathDist(test.get(0).getKey(),test.get(1).getKey())==0.1);

    }


    @Test
    void saveLoad() {
        G.clear();
        ArrayList<NodeData> v = new ArrayList<NodeData>();
        int k = 0;
        for (double i = 0; i < 3; i++) {
            double j = 10 - i;
            v.add(new Node(i + "," + j + "," + "0.0", k));
            k++;
        }
        G.SetNodeData(v);
        G.connect(1, 2, 0);
        G.connect(2, 0, 1);
        G.connect(1, 0, 2);
        DWGA algo = new DWGA();
        DWG temp=G.copy();
        algo.init(G);
        algo.save("or.json");
        algo.g.clear();
        algo.load("or.json");
        for (int i=0;i<algo.g.Nodes.size();i++){
            Assertions.assertTrue(((Node)(temp.Nodes.get(i))).ugual((Node)(algo.g.Nodes.get(i))));
        }
        Iterator<EdgeData> E1=temp.edgeIter();
        Iterator<EdgeData>E2=algo.g.edgeIter();
        while(E1.hasNext()){
            EdgeData e1=E1.next();
            EdgeData e2=E2.next();
            Assertions.assertTrue(((Edge)(e1)).equal((Edge)(e2)));

        }


    }

}