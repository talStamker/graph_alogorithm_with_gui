import api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuFrame extends JFrame implements ActionListener {
    int i=1;
    JButton ADDvert,ADDEdge,RemoveVert,RemoveEdge,Save,Load,Tsp,Center,ShortestPath,ShortestPathDist,IsConnected,Refresh;
    DirectedWeightedGraphAlgorithms that;
    Paint pai;
    int id;
     MenuFrame(DirectedWeightedGraphAlgorithms alg){
         that=alg;
         id=alg.getGraph().nodeSize();
         pai = new Paint(alg.getGraph());
         pai.SetScales(600, 400);


         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(1000, 600);
         ADDvert = new JButton("Add Vertex");
         ADDvert.setBounds(820, i*30, 150, 30);
         i++;
         ADDvert.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 double x=getRandomNumber(35.18,35.22);
                 double y=getRandomNumber(32.10,32.11);
                 String pos=x+","+y+","+0.0;
                 NodeData n=new Node(pos,id);
                 id++;
                 that.getGraph().addNode(n);
                 pai=new Paint(that.getGraph());

             }
         });
         ADDvert.addActionListener(e -> this.repaint());
         ADDEdge=new JButton("Add Edge");
         ADDEdge.setBounds(820, i*30, 150, 30);
         ADDEdge.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String src=JOptionPane.showInputDialog("Enter a src");
                 String dest=JOptionPane.showInputDialog("enter dest");
                 String w=JOptionPane.showInputDialog("enter Wieght");
                 NodeData n1=that.getGraph().getNode(Integer.parseInt(src));
                 NodeData n2=that.getGraph().getNode(Integer.parseInt(dest));
                 if(n1==null||n2==null){
                     JOptionPane.showMessageDialog(null,"doesn`t exict","title",JOptionPane.WARNING_MESSAGE);
                 }
                 else {
                     that.getGraph().connect(Integer.parseInt(src),Integer.parseInt(dest),Double.parseDouble(w));
                     pai =new Paint(that.getGraph());

                 }
             }
         });
         i++;
         RemoveVert=new JButton("Remove Vertex");
         RemoveVert.setBounds(820, i*30, 150, 30);
         RemoveVert.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String id=JOptionPane.showInputDialog("Enter a key number");

                     NodeData n=that.getGraph().removeNode(Integer.parseInt(id));
                     System.out.print(" menu "+n.getKey());
                     if(n==null){
                         JOptionPane.showMessageDialog(null,"doesn`t exist","title",JOptionPane.WARNING_MESSAGE);
                     }
                     else {
                         pai=new Paint(that.getGraph());

                     }
             }



         });
         i++;
         RemoveVert.addActionListener(e -> this.repaint());
         RemoveEdge=new JButton("Remove Edge");
         RemoveEdge.setBounds(820, i*30, 150, 30);
         RemoveEdge.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String src=JOptionPane.showInputDialog("Enter a src");
                 String dest=JOptionPane.showInputDialog("enter dest");
                 EdgeData edge=that.getGraph().removeEdge(Integer.parseInt(src),Integer.parseInt(dest));
                 pai=new Paint(that.getGraph());
                 if (edge==null){
                     JOptionPane.showMessageDialog(null,"doesn`t exict","title",JOptionPane.WARNING_MESSAGE);
                 }
             }
         });
         i++;
         Save=new JButton("Save");
         Save.setBounds(820, i*30, 150, 30);
         Save.addActionListener(this);
         i++;
         Load=new JButton("Load");
         Load.setBounds(820, i*30, 150, 30);
         Load.addActionListener(this);
         i++;
         Tsp=new JButton("Tsp");
         Tsp.setBounds(820, i*30, 150, 30);
         Tsp.addActionListener(this);
         i++;
         Center=new JButton("Center");
         Center.setBounds(820, i*30, 150, 30);
         Center.addActionListener(this);
         i++;
         ShortestPath=new JButton("ShortestPath");
         ShortestPath.setBounds(820, i*30, 150, 30);
         ShortestPath.addActionListener(this);
         i++;
         ShortestPathDist=new JButton("ShortestPathDist");
         ShortestPathDist.setBounds(820, i*30, 150, 30);
         ShortestPathDist.addActionListener(this);
         i++;
         IsConnected=new JButton("IsConnected");
         IsConnected.setBounds(820, i*30, 150, 30);
         IsConnected.addActionListener(this);
         i++;
         Refresh=new JButton("Refresh");
         Refresh.setBounds(820, i*30, 150, 30);
         i++;
         Refresh.addActionListener(e -> this.repaint());

         this.add(Refresh);
         this.add(RemoveEdge);
         this.add(RemoveVert);
         this.add(ADDEdge);
         this.add(ADDvert);
         this.add(Save);
         this.add(Load);
         this.add(Tsp);
         this.add(Center);
         this.add(ShortestPath);
         this.add(ShortestPathDist);
         this.add(IsConnected);
         this.add(pai);
         this.setVisible(true);
     }
     @Override
     public void actionPerformed(ActionEvent e){
         if(e.getSource()==Save){
             String ans=JOptionPane.showInputDialog("What is the name of the file?");
             that.save(ans);
         }else if(e.getSource()==Load){
             String ans=JOptionPane.showInputDialog("What is the name of the file?");
             System.out.print(ans);
             DirectedWeightedGraphAlgorithms gra=new DWGA();
             gra.load(ans);
             new MenuFrame(gra);
         }else if(e.getSource()==Tsp){
             JOptionPane.showMessageDialog(null,"Enter cities one after the other if you want to finish enter -1","Tsp",JOptionPane.PLAIN_MESSAGE);
             String st=JOptionPane.showInputDialog("What is the city enter key");
             ArrayList<NodeData> cities=new ArrayList<NodeData>();
             while (!st.equals("-1")){
                 NodeData N=that.getGraph().getNode(Integer.parseInt(st));
                 if(N==null){
                     st=JOptionPane.showInputDialog("this node is not exist enter again the city key");
                 }else{
                     cities.add(N);
                     st=JOptionPane.showInputDialog("What is the city enter key");
                 }
             }
             List<NodeData> ans=that.tsp(cities);
             String path="";
             int j=0;
             for(;j<ans.size()-1;j++){
                 path+=ans.get(j).getKey()+" --> ";
             }
             path+=ans.get(j).getKey();
             JOptionPane.showMessageDialog(null,"this is the path of tsp: "+path,"Tsp",JOptionPane.PLAIN_MESSAGE);
         }else if(e.getSource()==Center){
             NodeData n=that.center();
             JOptionPane.showMessageDialog(null,"this is the center: "+n.getKey(),"Center",JOptionPane.PLAIN_MESSAGE);
         }else if(e.getSource()==ShortestPath){
             String ans=JOptionPane.showInputDialog("What is your source and destination will put them in shape: src,dest");
             String[] temp=ans.split(",");
             int src=Integer.parseInt(temp[0]);
             int dest=Integer.parseInt(temp[1]);
             List<NodeData> arr=that.shortestPath(src,dest);
             String path="";
             int j=0;
             for(;j<arr.size()-1;j++){
                 path+=arr.get(j).getKey()+" --> ";
             }
             path+=arr.get(j).getKey();
             JOptionPane.showMessageDialog(null,"this is the path: "+path,"ShortestPath",JOptionPane.PLAIN_MESSAGE);

         }else if(e.getSource()==ShortestPathDist){
             String ans=JOptionPane.showInputDialog("What is your source and destination will put them in shape: src,dest");
             String[] temp=ans.split(",");
             int src=Integer.parseInt(temp[0]);
             int dest=Integer.parseInt(temp[1]);
             double w=that.shortestPathDist(src,dest);
             JOptionPane.showMessageDialog(null,"the short path distance between "+src+" to "+ dest+" is: "+w,"ShortestPathDist",JOptionPane.PLAIN_MESSAGE);
         }else if(e.getSource()==IsConnected){
             boolean ans=that.isConnected();
             if(ans){
                 JOptionPane.showMessageDialog(null,"this is connected graph","IsConnected?",JOptionPane.PLAIN_MESSAGE);
             }else{
                 JOptionPane.showMessageDialog(null,"this is not connected graph","IsConnected?",JOptionPane.PLAIN_MESSAGE);
             }
         }



     }

    public double getRandomNumber(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }


}
