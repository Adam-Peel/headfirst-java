import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.sound.midi.ShortMessage.*;

public class MiniMusicPlayer3 {
  private MyDrawPanel panel;
  private Random random = new Random();

  public static void main(String[] args) {
    MiniMusicPlayer3 mini = new MiniMusicPlayer3();
    mini.go();
  }

  public void setUpGui() {
    JFrame frame = new JFrame("My First Music Video");
    panel = new MyDrawPanel();
    frame.setContentPane(panel);
    frame.setBounds(30, 30, 300, 300);
    frame.setVisible(true);
  }

  public void go() {
    setUpGui();

    try {
      Sequencer sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequencer.addControllerEventListener(panel, new int[]{127});
      Sequence seq = new Sequence(Sequence.PPQ, 4);
      Track track = seq.createTrack();

      int note;
      for (int i = 0; i < 60; i += 4) {
        note = random.nextInt(50) + 1;
        track.add(makeEvent(NOTE_ON, 1, note, 100, i));
        track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
        track.add(makeEvent(NOTE_OFF, 1, note, 100, i + 2));
      }

      sequencer.setSequence(seq);
      sequencer.start();
      sequencer.setTempoInBPM(120);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // for some commands one = note, and for others one = instrument
  public static MidiEvent makeEvent(int cmd, int chnl, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage msg = new ShortMessage();
      msg.setMessage(cmd, chnl, one, two);
      event = new MidiEvent(msg, tick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }

  class MyDrawPanel extends JPanel implements ControllerEventListener {
    private boolean msg = false;

    public void controlChange(ShortMessage event) {
      msg = true;
      repaint();
    }

    public void paintComponent(Graphics g) {
      if (msg) {
        int r = random.nextInt(250);
        int gr = random.nextInt(250);
        int b = random.nextInt(250);

        g.setColor(new Color(r, gr, b));

        int height = random.nextInt(120) + 10;
        int width = random.nextInt(120) + 10;

        int xPos = random.nextInt(40) + 10;
        int yPos = random.nextInt(40) + 10;

        g.fillRect(xPos, yPos, width, height);
        msg = false;
      }
    }
  }

}

/*

The loop: for (int i = 0; i < 60; i += 4) — values of i are: 0, 4, 8, ..., 56 (15 iterations).
For each i add, in this order:
NOTE_ON at tick i
CONTROL_CHANGE (controller number = 127) at tick i
NOTE_OFF at tick i+2

Because we call track.add(...) sequentially, the events at the same tick are stored in track order: NOTE_ON then CONTROL_CHANGE.

The Sequencer processes events at each tick; when it processes a CONTROL_CHANGE with controller 127 it notifies listeners registered for controller 127 (panel).

Sequencer invokes controlChange(...) on its own thread. In this code, it calls repaint().

Timeline:

Iteration 1 (i = 0)

Tick 0:
Sequencer processes NOTE_ON(channel 1, note = note0). (This starts the note.)
Sequencer processes CONTROL_CHANGE(controller = 127, value = 0).
Sequencer calls panel.controlChange(ShortMessage) on the Sequencer.
controlChange sets msg = true and calls repaint().

Tick 2:
Sequencer processes NOTE_OFF(channel 1, note = note0). (Stops the note.)
On drawpanel, since msg == true, a colored rectangle is drawn and msg is set back to false.
Iteration 2 (i = 4)

Tick 4:
NOTE_ON(channel 1, note = note1) processed.
CONTROL_CHANGE(controller = 127) processed → panel.controlChange(...) called → msg = true; repaint() enqueued.
Tick 6:
NOTE_OFF(channel 1, note = note1) processed.
EDT:
paintComponent runs (draws next rectangle) when it processes the repaint event.
Iteration 3 (i = 8)

Tick 8:
NOTE_ON(note2), then CONTROL_CHANGE(controller = 127) → controlChange invoked → repaint requested.
Tick 10:
NOTE_OFF(note2)
EDT:
paintComponent runs and draws the third rectangle.
Pattern for all iterations

For each i in {0,4,8,...,56}:
At tick i: NOTE_ON then CONTROL_CHANGE(127) → controlChange invoked → repaint() requested.
At tick i+2: NOTE_OFF for that note.

 */