import api.*;
import api.Paint;

import java.awt.Color;
import java.awt.Font;;


import java.awt.*;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.Scanner;



/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new DWG();
        DirectedWeightedGraphAlgorithms temp = new DWGA();
        temp.load(json_file);


        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new DWGA();
        ans.load(json_file);


        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
//        int i=1;
//        alg.save("ex.json");
//        alg.save("yalla.json");
//        Iterator<NodeData> V=alg.getGraph().nodeIter();
//        double maxX=0;
//        double minX=Double.MAX_VALUE;
//        double maxY=0;
//        double minY=Double.MAX_VALUE;
//        while (V.hasNext()){
//            NodeData curr=V.next();
//            maxX=Math.max(maxX,curr.getLocation().x());
//            minX=Math.min(minX,curr.getLocation().x());
//            maxY=Math.max(maxY,curr.getLocation().y());
//            minY=Math.min(minY,curr.getLocation().y());
//        }
//        double ABSX=Math.abs(maxX-minX);
//        double ScaleX=600/ABSX;
//        double ABSY=Math.abs(maxY-minY);
//        double ScaleY=600/ABSY;
//        Paint pai = new Paint(alg.getGraph());
//        JFrame fram = new JFrame();
//        pai.SetScales(600, 400);
//
//
//        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fram.setSize(1000, 600);
//        JButton ADDvert = new JButton("Add Vertex");
//        ADDvert.setBounds(820, i*30, 150, 30);
//        i++;
//        JButton ADDEdge=new JButton("Add Edge");
//        ADDEdge.setBounds(820, i*30, 150, 30);
//        i++;
//        JButton RemoveVert=new JButton("Remove Vertex");
//        RemoveVert.setBounds(820, i*30, 150, 30);
//        i++;
//        JButton RemoveEdge=new JButton("Remove Edge");
//        RemoveEdge.setBounds(820, i*30, 150, 30);
//        i++;
//        JButton runalgo=new JButton("Run algoritem");
//        runalgo.setBounds(820, i*30, 150, 30);
//        i++;
//        fram.add(runalgo);
//        fram.add(RemoveEdge);
//        fram.add(RemoveVert);
//        fram.add(ADDEdge);
//        fram.add(ADDvert);
//        fram.add(pai);
//        fram.setVisible(true);
        new MenuFrame(alg);


    }

    public static void main(String[] args) {
        System.out.println("Enter json file from G1 to G3");
        String input =args[0];
        runGUI(input);




    }
}