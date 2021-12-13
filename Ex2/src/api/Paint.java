package api;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.*;

public class Paint extends JPanel {
    DirectedWeightedGraph graph;
    double scaleX=0,scaleY=0;
    double minX,maxX,minY,maxY;
    public Paint(DirectedWeightedGraph j){
        super();
        this.setBackground(Color.white);
        this.graph = j;
        MaxX();
        MaxY();
        MinX();
        MinY();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("name", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g2.setColor(Color.red);
        int x1;
        int y1;
        NodeData node = null;
        EdgeData edge = null;
        int y2;
        int x2;

        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = n.next();
            if (node==null)
                continue;
            x2 =(int) ((node.getLocation().x() - minX)*scaleX);
            y2 = (int)((node.getLocation().y()- minY)*scaleY);
            g2.drawOval(x2+55,y2+55,15,15);

        }
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = n.next();
            if(node==null)
                continue;
            int id = node.getKey();
            g2.setFont(new Font("zur",Font.PLAIN , 10));
            x1 = (int)((node.getLocation().x() - minX) * scaleX);
            y1 = (int)((node.getLocation().y() - minY) * scaleY);
            g2.drawString("id:" + id,  x1+50,  y1+40);

            for (Iterator<EdgeData> e = this.graph.edgeIter(node.getKey()); e.hasNext(); ) {
                edge = e.next();
                double weight = edge.getWeight();
                x2 = (int)((graph.getNode(edge.getDest()).getLocation().x() - minX) * scaleX);
                y2 = (int)((graph.getNode(edge.getDest()).getLocation().y() - minY) * scaleY);
                x1 = (int)((node.getLocation().x() - minX) * scaleX);
                y1 = (int)((node.getLocation().y() - minY) * scaleY);

                g2.drawLine(x1 + 60,  y1 + 60, x2 + 60, y2 + 60);
                g2.setFont(new Font("zur",Font.PLAIN , 10));
                g2.drawString("id:" + id,  x1+50,  y1+40);

            }
            g2.setFont(new Font("zur",Font.PLAIN , 10));

        }
    }

    public void MaxX() {

        double highLoc = 0;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (iter==null)
                continue;
            else {
                if (this.graph.getNode(iter.getKey()).getLocation().x() > highLoc) {
                    highLoc = this.graph.getNode(iter.getKey()).getLocation().x();

                }
            }
        }
        maxX= highLoc;
    }

    public void MaxY() {

        double highLoc = 0;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (iter==null)
                continue;
            if (this.graph.getNode(iter.getKey()).getLocation().y() > highLoc) {
                highLoc = this.graph.getNode(iter.getKey()).getLocation().y();

            }
        }
        maxY= highLoc;
    }

    public void MinX() {

        double lowLoc = Double.MAX_VALUE;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (iter==null)
                continue;
            if (this.graph.getNode(iter.getKey()).getLocation().x() < lowLoc) {
                lowLoc = this.graph.getNode(iter.getKey()).getLocation().x();
            }
        }

        minX= lowLoc;
    }

    public void MinY() {

        double lowLoc = Double.MAX_VALUE;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (iter==null)
                continue;
            if (this.graph.getNode(iter.getKey()).getLocation().y() < lowLoc) {
                lowLoc = this.graph.getNode(iter.getKey()).getLocation().y();

            }
        }
        minY= lowLoc;
    }
    public void SetScales(int Scalexnum,int Scaleynum){
        double sumX = maxX - minX;
        scaleX = Scalexnum / sumX;
        double sumY = maxY - minY;
        scaleY = Scaleynum / sumY;


    }
    public  void SetGrap(DirectedWeightedGraphAlgorithms G){
        this.graph=G.getGraph();
    }
}
