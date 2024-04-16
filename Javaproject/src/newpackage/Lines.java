/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JToggleButton;

/**
 *
 * @author meraa
 */
public class Lines {
     
        private Coordinates start;
        private Coordinates end;
        private Color color;
       Color chosenColor;
        // Vector<Lines> freeline = new Vector<>();
         Vector<Lines> line = new Vector<>();
         Vector<Lines> dotline = new Vector<>();
       private boolean isdot;
       JToggleButton Line;

        public Lines(Coordinates start, Coordinates end, Color color) {
            this.start = start;
            this.end = end;
            this.color = color;
        }
        
         public Lines(Coordinates start, Coordinates end, Color color, boolean bool) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.isdot =bool;
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
        
        public void lineloop (Graphics g)
        {
             for (int i = 0; i < line.size(); i ++) 
             {
                Lines lin = line.get(i);
                g2d.setColor(lin.getColor());
                g2d.setStroke(new BasicStroke(5));
                g.drawLine( lin.start.getx(), lin.start.gety() , lin.end.getx(), lin.end.gety());
              }
        } 
        
        public void drawl (Graphics g)
        {
           // Color chosenColor = null;
             g2d.setColor(chosenColor);
             g2d.setStroke(new BasicStroke(5));
             g.drawLine(start.getx(), start.gety(), end.getx(), end.gety());
        }
        
        
        
          public void displayDottedLines(Graphics2D g2d ) {
        if (lineToggleButton.isSelected()) {
            for (int i = 0; i < dotline.size(); i++) {
                Lines dotLine = dotline.get(i);
                g2d.setColor(dotLine.getColor());
                g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
                g2d.drawLine(dotLine.getStart().getx(), dotLine.getStart().gety(),
                        dotLine.getEnd().getx(), dotLine.getEnd().gety());
            }
        }
    }
         
          public void drawDottedLines(Graphics2D g2d) {
        float[] dashPattern = {10}; // Adjust the dash length as needed
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0));

        g2d.setColor(color);
        g2d.drawLine(start.getx(), start.gety(), end.getx(), end.gety());
    }
         
 

    Lines get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isDotted() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isPointOnLine(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
