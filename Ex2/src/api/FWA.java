package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FWA {
    // A Java program for Floyd Warshall All Pairs Shortest
// Path algorithm.
    final static double INF = Double.MAX_VALUE-100;
    double[][] graph;
    double[][] dist;
    int[][] next;
    DirectedWeightedGraph gr;
    public FWA(DWG g){
        gr=g;
        graph=new double[g.nodeSize()][g.nodeSize()];
        Iterator<EdgeData> it=gr.edgeIter();
        for(int i=0;i< gr.nodeSize();i++){
            for(int J=0;J< gr.nodeSize();J++){
                if(i==J){graph[i][i]=0;}
                else {
                    graph[i][J]=INF;
                }
            }
        }
        while (it.hasNext()){
            EdgeData curr= it.next();
            graph[curr.getSrc()][curr.getDest()]=curr.getWeight();

        }
        floydWarshall(graph);


    }
    public double ShortPach(int src,int dest){
        if (dist[src][dest]>=INF)
            return -1;
        return dist[src][dest];
    }
    public List<NodeData> constructPath(int u,int v)
    {

        // If there's no path between
        // node u and v, simply return
        // an empty array
        if (next[u][v] == -1)
            return null;

        // Storing the path in a vector
        List<NodeData> path = new LinkedList<NodeData>();
        path.add(gr.getNode(u));

        while (u != v)
        {
            u = next[u][v];
            path.add(gr.getNode(u));
        }
        return path;
    }
    public boolean IsConceted(){
        for (int i=0;i< gr.nodeSize();i++){
            for (int j=0;j< gr.nodeSize();j++){
                if(dist[i][j]>=INF)
                    return false;
            }

        }
        return true;
    }

    public NodeData Center(){
        double[] e=new double[gr.nodeSize()];
        ArrayList<Node> c=new ArrayList<Node>();
        double rad=INF;
        for (int i=0;i< gr.nodeSize();i++){
            e[i]=0;
        }
        for (int i = 0; i < gr.nodeSize(); i++) {
            for (int j = 0; j < gr.nodeSize(); j++) {

                e[i] = Math.max(e[i], dist[i][j]);
            }
        }
        for (int i = 0; i < gr.nodeSize(); i++){
            rad=Math.min(rad,e[i]);
        }
        for (int i = 0; i < gr.nodeSize(); i++){
            if (e[i]==rad)
                c.add(new Node(gr.getNode(i).getLocation().x()+","+gr.getNode(i).getLocation().y()+","+gr.getNode(i).getLocation().z()+",",gr.getNode(i).getKey()));
        }
        return c.get(0);

    }




    public  void floydWarshall(double graph[][])
    {
        dist= new double[gr.nodeSize()][gr.nodeSize()];
        next=new int[gr.nodeSize()][gr.nodeSize()];
        for (int i=0;i< gr.nodeSize();i++){
            for (int j=0;j<gr.nodeSize();j++){
                dist[i][j]=graph[i][j];
                if (graph[i][j] == INF)
                    next[i][j] = -1;
                else
                    next[i][j] = j;
            }
        }
        for(int k = 0; k < gr.nodeSize(); k++)
        {
            for(int i = 0; i < gr.nodeSize(); i++)
            {
                for(int j = 0; j < gr.nodeSize(); j++)
                {

                    // We cannot travel through
                    // edge that doesn't exist
                    if (dist[i][k] == INF ||
                            dist[k][j] == INF)
                        continue;

                    if (dist[i][j] > dist[i][k] +
                            dist[k][j])
                    {
                        dist[i][j] = dist[i][k] +
                                dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }
}