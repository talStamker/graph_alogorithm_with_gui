package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class DWGTest {
    DWG G=new DWG();
    @Test
    public void testNodeIte(){
        G.clear();
                ArrayList<NodeData> v=new ArrayList<NodeData>();
                      int k=0;
                       for (double i=0;i<3;i++){
                           double j=10-i;
                           v.add(new Node(i+","+j+","+"0.0",k));
                            k++;
                       }
                             G.SetNodeData(v);
                     Iterator<NodeData> arr=  G.nodeIter();
                     int i=0;
                     while (arr.hasNext()){
                         NodeData n=arr.next();
                         Node m1=new Node(n.getLocation().x()+","+n.getLocation().y()+","+n.getLocation().z(),n.getKey());
                         Node m2= new Node(G.Nodes.get(i).getLocation().x()+","+G.Nodes.get(i).getLocation().y()+","+G.Nodes.get(i).getLocation().z(),G.Nodes.get(i).getKey());
                         Assertions.assertTrue(m1.ugual(m2));
                         i++;
                     }
    }
    @Test
    public void testEdgeIt() {
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
        Iterator<EdgeData> arr = G.edgeIter();
        while (arr.hasNext()) {
            EdgeData E1 = arr.next();
            Edge m1 = new Edge(E1.getSrc(), E1.getDest(), E1.getWeight(), E1.getInfo(), E1.getTag());
            EdgeData E2 = G.getEdge(E1.getSrc(), E1.getDest());
            Edge m2 = new Edge(E2.getSrc(), E2.getDest(), E2.getWeight(), E2.getInfo(), E2.getTag());
            Assertions.assertTrue(m1.equal(m2));
        }
    }
    @Test
    public void testedgItN(){
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
        Iterator<EdgeData> arr = G.edgeIter(1);
        while(arr.hasNext()){
            Assertions.assertEquals(1,arr.next().getSrc());
        }

    }
    @Test
    public void testSm(){
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
        Assertions.assertEquals(6,G.getMC());
        Assertions.assertEquals(3,G.nodeSize());
        Assertions.assertEquals(3,G.edgeSize());
    }

}