/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */




package gr.auth.ee.dsproject.crush;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderListener implements ChangeListener
{
  public void stateChanged (ChangeEvent e)
  {
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
      // int fps = (int)source.getValue();
    }
  }
}
