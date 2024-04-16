package newpackage;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import newpackage.Ovals;
import newpackage.Rect;

public class Panel extends JPanel {
    Vector<Lines> freeline = new Vector<>();
    Vector<Lines> eraserr = new Vector<>();
    Vector<Lines> dotfreeline = new Vector<>();
    Vector<Lines> line = new Vector<>();
    Vector<Lines> dotline = new Vector<>();
    Vector<Ovals> oval = new Vector<>();
    Vector<Ovals> dotoval = new Vector<>();
    Vector<Rect> rect = new Vector<>();
    Vector<Rect> dotRect = new Vector<>();
    Vector<Rect> filledRect = new Vector<>();
    Vector<Ovals> filledOval = new Vector<>();
   
    public Coordinates start;
    public Coordinates end;
    public Ovals o ;
    Rect r;
    public Lines l;
    JToggleButton FreeLine;
    JToggleButton Line;
    JToggleButton Oval;
    JToggleButton rectangle;
    JToggleButton Colors;
    JToggleButton eraser;
    Color chosenColor;
    JToggleButton clear;
    JButton save;
    
    JCheckBox dottedCheckbox;
    JCheckBox filledCheckbox;
    // Variable to track eraser mode
      private boolean eraserMode = false;
     private int dotted = 0; // 0 for solid, 1 for dotted
     private boolean isDotted = true;
    
    public Panel() {
        this.setBackground(new Color(249,249,249));

        // Create a ButtonGroup for mutually exclusive button selection
        ButtonGroup buttonGroup = new ButtonGroup();

        // Create Free Line button
        FreeLine = new JToggleButton("Free Line");
        FreeLine.setBackground(new Color (249,249,249));
        FreeLine.setBounds(10, 20, 100, 30);
       // FreeLine.setActionCommand("FreeLine");
        buttonGroup.add(FreeLine);
        add(FreeLine);

        // Create Line button
        Line = new JToggleButton("Line");
        Line.setBackground(new Color(249,249,249));
        Line.setBounds(120, 20, 100, 30);
       // Line.setActionCommand("Line");
        buttonGroup.add(Line);
        add(Line);
        
        // Create Oval button
        Oval = new JToggleButton("Oval");
        Oval.setBackground(new Color(249,249,249));
        Oval.setBounds(320, 20, 100, 30);
        buttonGroup.add(Oval);
        add(Oval);
        
        // Create Rectangle button
        rectangle = new JToggleButton("Rectangle");
        rectangle.setBackground(new Color(249,249,249));
        rectangle.setBounds(380, 20, 100, 30);
        buttonGroup.add(rectangle);
        add(rectangle);
        
        // Create Color button
        Colors = new JToggleButton("Color");
        Colors.setBackground(new Color(249,249,249));
        Colors.setBounds(260, 20, 100, 30);
        buttonGroup.add(Colors);
        add(Colors);
        
         // Create dotted checkbox
        dottedCheckbox = new JCheckBox("Dotted");
        dottedCheckbox .setBackground(new Color(249,249,249));
        dottedCheckbox.setBounds(600, 40, 100, 30);
        add(dottedCheckbox);
        
        // Create filled checkbox
        filledCheckbox = new JCheckBox("Filled");
        filledCheckbox.setBackground(new Color(249,249,249));
        filledCheckbox.setBounds(700, 40, 100, 30);
        add(filledCheckbox);
     
        // Create eraser button
        eraser = new JToggleButton("Eraser");
        eraser.setBackground(new Color(249,249,249));
        eraser.setBounds(400, 20, 100, 30);
        buttonGroup.add(eraser);
        add(eraser);
        
         // Create clear button
         clear = new JToggleButton(" clear");
         clear.setBackground(new Color (249,249,249));
         clear.setBounds(500, 20, 100, 30);
          buttonGroup.add( clear);
           add( clear);
           
          // Create save button
          save = new JButton("Save");
          save.setBackground(new Color(249, 249, 249));
          save.setBounds(540, 20, 100, 30);
          add(save);
       
        // Connect clear button to ActionListener
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
                repaint();
            }

             private void clearAll() {
                freeline.clear();
                line.clear();
                oval.clear();
                rect.clear();
                filledRect.clear();
                filledOval.clear();
                dotfreeline.clear(); 
                dotline.clear();
                dotoval.clear();
                dotRect.clear();}
        });
     
         // Connect dotted checkbox to ItemListener
        dottedCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isDotted = dottedCheckbox.isSelected();
            }
        });

        // Connect Colors button to ActionListener
        Colors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Colors.isSelected()) {
                    // Show color dialog when Color button is selected
                    chosenColor = JColorChooser.showDialog(null, "Choose a color", chosenColor);
                }
            }
        });
      
        // Connect save button to ActionListener
        save.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
           savePanelAsImage();
          }

        private void savePanelAsImage() {
          try {
              
               BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
               Graphics2D g2d = image.createGraphics();
               paint(g2d);
               g2d.dispose();
               File file = new File("saved_image.png");
               ImageIO.write(image, "png", file);
                System.out.println("Panel saved as " + file.getAbsolutePath());
                  } catch (IOException ex) {
                    ex.printStackTrace();}
               }
           });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                start = new Coordinates(e.getX(), e.getY());
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                end = new Coordinates(e.getX(), e.getY());

            if (FreeLine.isSelected()) {
                 if (dottedCheckbox.isSelected()) 
                 {dotfreeline.add(new Lines(start, end, chosenColor,true));
                 }else {freeline.add(new Lines(start, end, chosenColor));
                 repaint();}
           } else if (Line.isSelected()) {
                    if (dottedCheckbox.isSelected()) {
                      dotline.add(new Lines(start, end, chosenColor,true));
                    } else {
                       line.add(new Lines(start, end, chosenColor));
                     }
           } else if (Oval.isSelected()) {
                  if (dottedCheckbox.isSelected()) {
                      dotoval.add(new Ovals(start, end, chosenColor,false,true));
                    } else {oval.add(new Ovals(start, end, chosenColor));}
                 if (filledCheckbox.isSelected()) {
                     filledOval.add(new Ovals(start, end, chosenColor, true,false));
                          }
          } else if (rectangle.isSelected()) {
                  if (dottedCheckbox.isSelected()) {
                      dotRect.add(new Rect(start, end, chosenColor,false,true));
                      } else {
                     rect.add(new Rect(start, end, chosenColor));
                       }
                  if (filledCheckbox.isSelected()) {
                     filledRect.add(new Rect(start, end, chosenColor, true,false));
                       }
           } else if (eraser.isSelected()) {
                  eraserr.add(new Lines(start, end, chosenColor));
                  repaint();
                       }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
          });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                end = new Coordinates(e.getX(), e.getY());

              if (FreeLine.isSelected()) {
                    if (dottedCheckbox.isSelected()) {
                     dotfreeline.add(new Lines(start, end, chosenColor,true));
                     start = end;
                    repaint();
                    } else {
                    freeline.add(new Lines(start, end, chosenColor));
                    start = end;
                    repaint();
                      }
                    
    
                } else if (Line.isSelected()) {
                    repaint();
                }else if (Oval.isSelected()) {
                    repaint();
                }else if (rectangle.isSelected()) {
                    repaint();
                }else if (eraser.isSelected()) {
                    eraserr.add(new Lines(start,end, chosenColor));
                    start = end;
                    repaint();
            }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

 
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
      
           for (int i = 0; i < freeline.size(); i ++) 
            {
              Lines freel = freeline.get(i);
              g2d.setColor(freel.getColor());
              g2d.setStroke(new BasicStroke(5));
              g.drawLine(freel.getStart().getx(), freel.getStart().gety(), freel.getEnd().getx(), freel.getEnd().gety());
            }
       
           if (Line.isSelected())
            {    
              // Draw the line related to the "Line" button
              g2d.setColor(chosenColor);
             g2d.setStroke(new BasicStroke(5));
              g.drawLine(start.getx(), start.gety(), end.getx(), end.gety());
            }
              
            for (int i = 0; i < line.size(); i ++) 
             {
                Lines lin = line.get(i);
                g2d.setColor(lin.getColor());
                g2d.setStroke(new BasicStroke(5));
                g.drawLine( lin.getStart().getx(), lin.getStart().gety(), lin.getEnd().getx(), lin.getEnd().gety());
              }

        
            //ovals 
           if (Oval.isSelected())
            { // Draw the ovalrelated to the "Oval" button
              g2d.setColor(chosenColor);
              g2d.setStroke(new BasicStroke(5));
              g.drawOval(start.getx(), start.gety() , end.getx()-start.getx(), end.gety()-start.gety());
            }
             for (int i = 0; i < oval.size(); i ++) 
            {
                Ovals ovl = oval.get(i);
                g2d.setColor(ovl.getColor( ) );
                g2d.setStroke(new BasicStroke(5));
                g.drawOval(ovl.getStart().getx(), ovl.getStart().gety(), ovl.getEnd().getx() - ovl.getStart().getx(), ovl.getEnd().gety() - ovl.getStart().gety());
            }
             
             //rectangles
           if (rectangle.isSelected())
            {
              g2d.setColor(chosenColor);
              g2d.setStroke(new BasicStroke(5));
              g.drawRect(start.getx(), start.gety() , end.getx()-start.getx(), end.gety()-start.gety());
            
            }
        
             for (int i = 0; i < rect.size(); i ++) 
            {
                Rect rec = rect.get(i);
                g2d.setColor (rec.getColor());
                g2d.setStroke(new BasicStroke(5));
                g.drawRect(rec.getStart().getx(), rec.getStart().gety(), rec.getEnd().getx() - rec.getStart().getx(), rec.getEnd().gety() - rec.getStart().gety());
            }

         
            if (filledCheckbox.isSelected())
            {
                 if (rectangle.isSelected())
                 {
                    g2d.setColor(chosenColor);
                    g2d.setStroke(new BasicStroke(5));
                    g.fillRect(start.getx(), start.gety() , end.getx()-start.getx(), end.gety()-start.gety());  }
            }
              
                    for (int i = 0; i < filledRect.size(); i ++) 
                    {
                        Rect rec = filledRect.get(i);
                        g2d.setColor(rec.getColor());
                        g2d.setStroke(new BasicStroke(5));
                        g2d.fillRect(rec.getStart().getx(), rec.getStart().gety(), rec.getEnd().getx() - rec.getStart().getx(), rec.getEnd().gety() - rec.getStart().gety());
                    }
             
             
            if (filledCheckbox.isSelected())
             {
                    if (Oval.isSelected())
                    {
                       g2d.setColor(chosenColor);
                       g2d.setStroke(new BasicStroke(5));
                       g.fillOval(start.getx(), start.gety() , end.getx()-start.getx(), end.gety()-start.gety());}
             }
                    for (int i = 0; i < filledOval.size(); i ++) 
                    {
                       Ovals ovl = filledOval.get(i);
                       g2d.setColor(ovl.getColor());
                       g2d.setStroke(new BasicStroke(5));
                       g.fillOval(ovl.getStart().getx(), ovl.getStart().gety(), ovl.getEnd().getx() - ovl.getStart().getx(), ovl.getEnd().gety() - ovl.getStart().gety());
                    }
              
           if (eraser.isSelected())
              {
              for (int i = 0; i < eraserr.size(); i ++) 
                {
                 Lines era = eraserr.get(i);
                 g2d.setColor(new Color(249,249,249));
                 g2d.setStroke(new BasicStroke(10));
                 g.drawLine(era.getStart().getx(), era.getStart().gety(), era.getEnd().getx(), era.getEnd().gety());
                }
             }    
           
      
      
       // Draw dotted lines, rectangles, and ovals 
       if (dottedCheckbox.isSelected()) {
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));

        if (Line.isSelected()) {
            g2d.setColor(chosenColor);
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawLine(start.getx(), start.gety(), end.getx(), end.gety());
        }
        if (rectangle.isSelected()) {
            g2d.setColor(chosenColor);
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawRect(start.getx(), start.gety(), end.getx() - start.getx(), end.gety() - start.gety());
        }

       if (Oval.isSelected()) {
            g2d.setColor(chosenColor);
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawOval(start.getx(), start.gety(), end.getx() - start.getx(), end.gety() - start.gety());
        }
      
    }
    
        for (Lines lin : dotline) {
            g2d.setColor(lin.getColor());
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawLine(lin.getStart().getx(), lin.getStart().gety(), lin.getEnd().getx(), lin.getEnd().gety());
        }
       for (Rect rec : dotRect) {
            g2d.setColor(rec.getColor());
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawRect(rec.getStart().getx(), rec.getStart().gety(), rec.getEnd().getx() - rec.getStart().getx(), rec.getEnd().gety() - rec.getStart().gety());
        }
       for (Ovals ovl : dotoval) {
            g2d.setColor(ovl.getColor());
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawOval(ovl.getStart().getx(), ovl.getStart().gety(), ovl.getEnd().getx() - ovl.getStart().getx(), ovl.getEnd().gety() - ovl.getStart().gety());
        }
     
        for (Lines freel : dotfreeline) {
            g2d.setColor(freel.getColor());
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 0));
            g2d.drawLine(freel.getStart().getx(), freel.getStart().gety(), freel.getEnd().getx(), freel.getEnd().gety());
        }
    }
  
   }

 
