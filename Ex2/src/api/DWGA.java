package api;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DWGA implements DirectedWeightedGraphAlgorithms {
    DWG g;
    FWA answers;
    public DWGA(){
        this.g=new DWG();
    }

    @Override
    public void init(DirectedWeightedGraph g) {

        ArrayList<NodeData> v=new ArrayList<NodeData>();
        ArrayList<EdgeData> e=new ArrayList<EdgeData>();
        Iterator<NodeData> it=g.nodeIter();
        Iterator<EdgeData> it2=g.edgeIter();
        this.g=new DWG();

        while (it.hasNext()){
            NodeData curr=it.next();
            this.g.addNode(new Node(curr.getLocation().x()+","+curr.getLocation().y()+","+curr.getLocation().z(),curr.getKey()));
        }
        while (it2.hasNext()){
            EdgeData E1=it2.next();
            this.g.connect(E1.getSrc(),E1.getDest(),E1.getWeight());
        }
        answers=new FWA(this.g);

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return this.g.copy();
    }

    @Override
    public boolean isConnected() {
        answers=new FWA(this.g);
        return answers.IsConceted();

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        answers=new FWA(this.g);
        return answers.ShortPach(src,dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        answers=new FWA(this.g);
        return answers.constructPath(src,dest);
    }

    @Override
    public NodeData center() {
        answers=new FWA(this.g);
        if (this.isConnected()==false)
            return null;
        return answers.Center();

    }

    private int nearestN(int i,List<NodeData> cities,double[][]c,boolean[]isvisited){
        double min=Double.MAX_VALUE;
        int index=cities.size();
        for (int j=0;j<cities.size();j++){
            if(i!=j){
                if(c[i][j]<min&&isvisited[j]==false){
                    min=c[i][j];
                    index=j;
                }
            }
        }
        if(index==cities.size())
            return -1;
        isvisited[index]=true;
        return index;
    }
    private boolean isDone(boolean[] isVisit){
        boolean ans=true;
        for (int i=0;i<isVisit.length;i++){
            ans=ans&isVisit[i];
            if(ans==false)
                return ans;
        }
        return ans;
    }
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        double[][] c = new double[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i != j) {
                    c[i][j] = shortestPathDist(cities.get(i).getKey(), cities.get(j).getKey());
                }
            }
        }
        double min = Double.MAX_VALUE;
        ArrayList<NodeData> ans = new ArrayList<NodeData>();
        for (int i = 0; i < cities.size(); i++) {
            boolean[] isVisited = new boolean[cities.size()];
            isVisited[i] = true;
            List<NodeData> temp = new ArrayList<NodeData>();
            int src = i;
            double w = 0;
            while (isDone(isVisited) == false) {
                int index = nearestN(src, cities, c, isVisited);
                temp.addAll(shortestPath(cities.get(src).getKey(), cities.get(index).getKey()));
                w += shortestPathDist(cities.get(src).getKey(), cities.get(index).getKey());
                src = index;
            }
            if (min > w) {
                min = w;
                ans = new ArrayList<>(temp);
            }

        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        try{
            GsonBuilder gsonBuilder=new GsonBuilder();
            JsonSerializer<DWG> serializer=new JsonSerializer<DWG>() {
                @Override
                public JsonElement serialize(DWG dwg, Type type, JsonSerializationContext jsonSerializationContext) {
                    JsonObject jsonObject=new JsonObject();
                    JsonArray arr2=new JsonArray();
                    Iterator<EdgeData> it2=dwg.edgeIter();
                    while(it2.hasNext()){
                        EdgeData e=it2.next();
                        JsonObject temp= new JsonObject();
                        temp.addProperty("src",e.getSrc());
                        temp.addProperty("w",e.getWeight());
                        temp.addProperty("dest",e.getDest());
                        arr2.add(temp);
                    }
                    jsonObject.add("Edges",arr2);
                    JsonArray arr1=new JsonArray();
                    Iterator<NodeData> it1 =dwg.nodeIter();
                    while(it1.hasNext()){
                        NodeData n=it1.next();
                        JsonObject temp=new JsonObject();
                        temp.addProperty("pos",n.getLocation().x()+","+n.getLocation().y()+","+n.getLocation().z());
                        temp.addProperty("id",n.getKey());
                        arr1.add(temp);
                    }
                    jsonObject.add("Nodes",arr1);
                    return jsonObject;
                }
            };
            gsonBuilder.registerTypeAdapter(DWG.class,serializer);
            gsonBuilder.setPrettyPrinting();
            Gson gson=gsonBuilder.create();
            String st=gson.toJson(g);
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(st);
            fileWriter.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        DirectedWeightedGraph graph;

        try{
            GsonBuilder gsonBuilder=new GsonBuilder();
            JsonDeserializer<DirectedWeightedGraph> deserializer=new JsonDeserializer<DirectedWeightedGraph>() {
                @Override
                public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    JsonObject jsonObject= jsonElement.getAsJsonObject();
                    DWG g=new DWG();
                    JsonArray jsonArray1=jsonObject.get("Nodes").getAsJsonArray();
                    for (int i=0;i<jsonArray1.size();i++) {
                        JsonObject jsonObject1 = jsonArray1.get(i).getAsJsonObject();
                        g.addNode(new Node(jsonObject1.get("pos").getAsString(), jsonObject1.get("id").getAsInt()));
                    }
                    JsonArray jsonArray2=jsonObject.get("Edges").getAsJsonArray();
                    for (int i=0;i<jsonArray2.size();i++){
                        JsonObject J=jsonArray2.get(i).getAsJsonObject();
                        Edge E=new Edge(J.get("src").getAsInt(),J.get("dest").getAsInt(),J.get("w").getAsDouble(),"",0);
                        g.connect(E.src,E.dest,E.w);
                    }
                   return g;
               }
            };
            gsonBuilder.registerTypeAdapter(DWG.class,deserializer);
            Gson gson=gsonBuilder.create();
            FileReader reader= new FileReader(file);
            graph=gson.fromJson(reader,DWG.class);
            this.init(graph);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}