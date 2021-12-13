package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DWG implements DirectedWeightedGraph{
    HashMap<Integer,Node> Nodes;
    HashMap<Integer,HashMap<Integer,Edge>> edgesk;
    ArrayList<Edge> Edges;
    int change=0,numOfEdge=0 ,id=0;
    //constructor
    public DWG(){
        Nodes=new HashMap<Integer,Node>();
        edgesk=new HashMap<Integer, HashMap<Integer,Edge>>();
        Edges=new ArrayList<Edge>();
    }
    //this function update the nodes in the graph by arraylist
    public void SetNodeData(ArrayList<NodeData> v2){
        for (int i=0;i<v2.size();i++){
            this.addNode(v2.get(i));
            id++;
        }
    }
    public boolean equeal(DWG G){
        for (int i=0;i<edgesk.size();i++){
            for (int j=0;j<edgesk.get(i).size();j++){
                if(this.edgesk.get(i)!=null) {
                    if(this.edgesk.get(i).get(j)!=null){
                        if (this.edgesk.get(i).get(j).equal(G.edgesk.get(i).get(j)))
                            return false;
                    }
                }
            }
        }
        for(int i=0;i<Nodes.size();i++){
            if(this.Nodes.get(i)!=null) {
                if (this.Nodes.get(i).equals(G.Nodes.get(i)))
                    return false;
            }
        }
        if(this.numOfEdge==G.numOfEdge)
            return false;
        return true;
    }
    //this function  update the nodes in the graph by node
    public void SetEdges(ArrayList<EdgeData> e){
        for (int i=0;i<e.size();i++) {
            this.connect(e.get(i).getSrc(),e.get(i).getDest(),e.get(i).getWeight());
        }
    }
    public void SetJsonEdges(){
        ArrayList<EdgeData> temp=new ArrayList<EdgeData>();
        temp.addAll(this.Edges);
        SetEdges(temp);
    }
    public void clear(){
        this.Nodes.clear();
        this.edgesk.clear();
        change=0;
        numOfEdge=0;
    }
    public double GetWighet(int src,int dest){return edgesk.get(dest).get(src).w;}

    public DWG copy(){
        DWG temp=new DWG();
        ArrayList<NodeData> V=new ArrayList<NodeData>();
        temp.SetNodeData(this.ITn(V));
        ArrayList<EdgeData> E=new ArrayList<EdgeData>();
        temp.SetEdges(this.Ite(E));

        return temp;
    }


    @Override
    public NodeData getNode(int key) {
        return Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return edgesk.get(dest).get(src);
    }

    @Override
    public void addNode(NodeData n) {
        GeoLocation G=n.getLocation();
        Node node=new Node(G.x()+","+G.y()+","+G.z(),n.getKey());
        Nodes.put(node.id,node);
        HashMap<Integer,Edge> e=new HashMap<Integer,Edge>();
        edgesk.put(node.id,e);
        id++;
        change++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(edgesk.get(dest).get(src)==null){
            numOfEdge++;

        }
        Edge E=new Edge(src,dest,w,"",0);
        edgesk.get(dest).put(src,E);
        Edges.add(E);
    change++;

    }
    public ArrayList<NodeData> ITn(ArrayList<NodeData> ARR){
        for (int i=0;i<id;i++){
            if (ARR==null)
                continue;
            ARR.add((NodeData)(Nodes.get(i)));
        }
        return ARR;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        ArrayList<NodeData> ARR= new ArrayList<NodeData>();
        int temp=change;
        Iterator<NodeData> it=ITn(ARR).iterator();
        if(temp!=change)
            throw new RuntimeException();
        return it;
    }

    public ArrayList<EdgeData> Ite(ArrayList<EdgeData> arr){
        Iterator<HashMap<Integer,Edge>> it1=edgesk.values().iterator();
        while (it1.hasNext()){
            HashMap<Integer,Edge> temp=it1.next();
            Iterator<Edge> it2=temp.values().iterator();
            while (it2.hasNext()){
                arr.add(it2.next());
            }

        }
        return arr;

    }
    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData>arr=new ArrayList<>();
        int temp=change;
        Iterator<EdgeData> it =Ite(arr).iterator();
        if(temp!=change)
            throw new RuntimeException();
        return it;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        ArrayList<EdgeData> ARR=new ArrayList<EdgeData>();
        for (int i=0;i<id;i++){
            if (edgesk.get(i)==null)
                continue;
            if(edgesk.get(i).get(node_id)!=null)
            ARR.add((EdgeData) (edgesk.get(i).get(node_id)));
        }
        int temp=change;
        Iterator<EdgeData> it=ARR.iterator();
        if(temp!=change)
            throw new RuntimeException();
        return it;
    }

    @Override
    public NodeData removeNode(int key) {

        Node node =Nodes.get(key);
        if(node!=null){
            Iterator<EdgeData> it=this.edgeIter(key);
            while (it.hasNext()){
                EdgeData e=it.next();
                System.out.print(e.getDest());
                this.removeEdge(key,e.getDest());
                change++;
            }
            change=change+edgesk.get(key).size()+1;
            edgesk.remove(key);
            Nodes.remove(key);
        }
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Edge E=edgesk.get(dest).get(src);
        if(E!=null) {
            edgesk.get(dest).remove(src);
            numOfEdge--;
        }
        change++;
        return E;
    }

    @Override
    public int nodeSize() {
        return Nodes.size();
    }

    @Override
    public int edgeSize() {
        return numOfEdge;
    }

    @Override
    public int getMC() {
        return change;
    }
}