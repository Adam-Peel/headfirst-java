import javax.swing.*;
import java.awt.*;
import java.util.Random;

class MyDrawPanel extends JPanel {

public void paintComponent (Graphics g) {
  Random random = new Random();
  int red = random.nextInt(256);
  int green = random.nextInt(256);
  int blue = random.nextInt(256);

  Color randomColor = new Color(red, green, blue);
  
  g.setColor(randomColor);
  g.fillRect(20, 50, 100, 100);
}

}
