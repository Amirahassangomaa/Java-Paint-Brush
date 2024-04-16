/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import javax.swing.JFrame;

/**
 *
 * @author meraa
 */
public class MainClass 
{
     public static void main (String[] args) 
    {
        
        JFrame f= new JFrame() ;
        f.setTitle("Paint Brush");
        Panel s =new Panel();
        f.setContentPane(s);
        f.setSize(1000, 1000);
        f.setVisible(true);
        
    }
}
