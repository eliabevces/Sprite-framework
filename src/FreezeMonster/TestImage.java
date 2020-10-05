package FreezeMonster;

import java.awt.Graphics; 
import java.awt.Image; 
import javax.swing.*; 
public class TestImage extends JPanel { 

   // Colocar a pasta image na raiz do projeto 
   String imageLocation = "/images/woody.png";
   ImageIcon ii = new ImageIcon(this.getClass().getResource(imageLocation));
   Image scaledImage = ii.getImage().getScaledInstance(30, 50, Image.SCALE_SMOOTH);

    
   public static void main (String args[]) { 
      JFrame i = new JFrame(); 
      TestImage ip = new TestImage(); 
      i.add(ip); 
      i.setSize(200, 200); 
      i.setVisible(true); 
      i.repaint(); 
   } 
     
   public void paintComponent (Graphics g) { 
      g.drawImage(scaledImage, 40, 60, this); 
   } 
} 