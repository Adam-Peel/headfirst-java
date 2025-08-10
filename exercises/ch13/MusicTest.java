import javax.sound.midi.*;

public class MusicTest {

  public void play() {

    try {
      Sequencer sequencer = MidiSystem.getSequencer();
      System.out.println("Sequencer get request successful");
    } catch(MidiUnavailableException e) {
      System.out.println("Error getting sequencer");
    }
  }

public static void main(String[] args) {
  MusicTest mt = new MusicTest();
  mt.play();
}

}
