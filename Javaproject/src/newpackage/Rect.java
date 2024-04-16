package newpackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JToggleButton;

public class Rect {
    private Coordinates start;
    private Coordinates end;
    private Color color;
    private boolean isfill;
    private boolean isdot;
    Vector<Rect> dotRect = new Vector<>();
    JToggleButton rectangle;

    public Rect(Coordinates start, Coordinates end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Rect(Coordinates start, Coordinates end, Color color, boolean bool) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.isfill = bool;
    }

    public Rect(Coordinates start, Coordinates end, Color color, boolean bool, boolean b) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.isfill = bool;
        this.isdot = b;
    }

    public void displayDottedRectangles(Graphics2D g2d, JToggleButton rectangle) {
        if (rectangle.isSelected()) {
            for (int i = 0; i < dotRect.size(); i++) {
                Rect dotRec = dotRect.get(i);
                g2d.setColor(dotRec.getColor());
                g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
                g2d.drawRect(dotRec.getStart().getx(), dotRec.getStart().gety(),
                        dotRec.getEnd().getx() - dotRec.getStart().getx(),
                        dotRec.getEnd().gety() - dotRec.getStart().gety());
            }
        }
    }

    public void drawDottedRectangles(Graphics2D g2d) {
        float[] dashPattern = {10}; // Adjust the dash length as needed
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0));
        g2d.setColor(color);
        g2d.drawRect(start.getx(), start.gety(), end.getx() - start.getx(), end.gety() - start.gety());
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

    Vector<Rect> rect = new Vector<>();

    public void displayrect(Graphics g) {
        for (int i = 0; i < rect.size(); i++) {
            Rect rec = rect.get(i);
            g2d.setColor(rec.getColor());
            g2d.drawRect(start.getx(), start.gety(), end.getx() - start.getx(), end.gety() - start.gety());
        }
    }
}
