package api;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DWGD implements JsonDeserializer<DirectedWeightedGraph>{
        public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            System.out.println("t");
            JsonObject jsonObject= jsonElement.getAsJsonObject();
            DWG gr=new DWG();
            System.out.println("tal");
            JsonArray jsonArray1=jsonObject.get("Nodes").getAsJsonArray();
            for (int i=0;i<jsonArray1.size();i++) {
                JsonObject jsonObject1 = jsonArray1.get(i).getAsJsonObject();
                String pos = jsonObject1.get("pos").getAsString();
                int id = jsonObject1.get("id").getAsInt();

                gr.addNode(new Node(pos, id));
            }
            System.out.println(jsonArray1.toString());
            JsonArray jsonArray2=jsonObject.get("Edges").getAsJsonArray();
            for (int i=0;i<jsonArray2.size();i++){
                JsonObject J=jsonArray2.get(i).getAsJsonObject();
                int src=J.get("src").getAsInt();
                double w=J.get("w").getAsDouble();
                int dest=J.get("dest").getAsInt();
                // Edge E=new Edge(src,dest,w,"",0);
                gr.connect(src,dest,w);
            }


            return gr;
        }
}
