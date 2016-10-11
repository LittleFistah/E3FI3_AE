package Max;

/**
 * Created by mstaeglich on 11.10.2016.
 *
 * DIESER HAT KEINE LUST MEHR DESSWEGEN SCHAUT DOCH BITTE BEI JAN IN DEN CODE :DDDDDDD
 */

import java.awt.*;
import java.awt.event.*;

public class AWTDemo {

   public static void main (String[] args){
      Frame fenster = new Frame("Testfenster");
      fenster.setSize(300, 200);

      MyListener myListener = new MyListener();

      Button b = new Button("press me");
      b.addActionListener(myListener);
      fenster.add(b);
      fenster.setVisible(true);

      System.out.println("Ende!");

   }


}
class MyListener implements ActionListener{

   @Override
   public void actionPerformed(ActionEvent e) {
      System.out.println("clicked");
   }

}
