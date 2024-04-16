/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JToggleButton;

/**
 *
 * @author meraa
 */
public class Ovals 
{
        private Coordinates start;
        private Coordinates end;
        private Color color;
        public int width;
        public int height;
       private boolean isfill;
       private boolean isdot;
        Vector<Ovals> dotoval = new Vector<>();
        JToggleButton Oval;

        public Ovals(Coordinates start, Coordinates end, Color color ) {
            this.start = start;
            this.end = end;
            this.color = color;
           // this.width=width;
            //this.height=height;
        }

     public Ovals(Coordinates start, Coordinates end, Color color, boolean bool,boolean b) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.isfill=bool;
            this.isdot =b;
        }

        public Coordinates getStart() {
            return start;
        }

        public Coordinates getEnd() {
            return end;
        }

        public Color getColor() {
            return color;
        }
        public int getheight()
        {
           height = end.gety()- start.gety()  ;
            return height ;
        }
        public int getwidth()
        {
            width = end.getx()- start.getx()  ;
            return width ;
        }
        
        
        public void displayDottedOvals(Graphics2D g2d) {
        if (ovalToggleButton.isSelected()) {
            for (int i = 0; i < dotoval.size(); i++) {
                Ovals dotOval = dotoval.get(i);
                g2d.setColor(dotOval.getColor());
                g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
                g2d.drawOval(dotOval.getStart().getx(), dotOval.getStart().gety(),
                        dotOval.getEnd().getx() - dotOval.getStart().getx(),
                        dotOval.getEnd().gety() - dotOval.getStart().gety());
            }
        }
    }

         public void drawDottedOvals(Graphics2D g2d) {
        float[] dashPattern = {10}; // Adjust the dash length as needed
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0));

        g2d.setColor(color);
        g2d.drawOval(start.getx(), start.gety(), end.getx() - start.getx(), end.gety() - start.gety());
    }
        
        

    class getEnd {

        public getEnd() {
        }
    }
}
