import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class TwoButtons {

  private JFrame frame;
  private JLabel label;
  public static void main(String[] args) {
    TwoButtons gui = new TwoButtons();
    gui.go();
  }

  public void go() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton labelButton = new JButton("Change Label");
    // labelButton.addActionListener(new LabelListener());
    labelButton.addActionListener(event -> label.setText("Changed by Lanbda!"));

    JButton colorButton = new JButton("Change Colour");
    // colorButton.addActionListener(new ColorListener());
    colorButton.addActionListener(event -> frame.getContentPane().repaint());

    label = new JLabel("I'm a label");
    MyDrawPanel drawPanel = new MyDrawPanel();
    
    frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
    frame.getContentPane().add(BorderLayout.EAST, labelButton);
    frame.getContentPane().add(BorderLayout.WEST, label);
    frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

    frame.setSize(500, 500);
    frame.setVisible(true);
  }

  // class LabelListener implements ActionListener {
  //   public void actionPerformed(ActionEvent event) {
  //     label.setText("You changed me!");
  //   }
  // }

  // class ColorListener implements ActionListener {
  //   public void actionPerformed(ActionEvent event) {
  //     frame.getContentPane().repaint();
  //   }
  // }

}
