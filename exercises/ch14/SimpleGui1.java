import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SimpleGui1 implements ActionListener {
  private JButton button;
  private JFrame frame;

  public static void main(String[] args) {
    SimpleGui1 gui = new SimpleGui1();
    gui.go();
  }

  public void go() {
    
    frame = new JFrame();
    button = new JButton("click me");
    MyDrawPanel panel = new MyDrawPanel();

    button.addActionListener(this);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(BorderLayout.SOUTH, button);
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }

  public void actionPerformed (ActionEvent event) {
    frame.getContentPane().repaint();
    button.setText("I've been clicked!");
  }
}
