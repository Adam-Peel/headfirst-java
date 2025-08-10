import java.util.stream.*;
import java.util.List;
import java.util.function.Function;

public class JukeBoxStreams {
  public static void main(String[] args) {
    List<Song> songs = new Songs().getSongs();
    List<Song> rockSongs = songs.stream()
                                  .filter (song -> song.getGenre().toLowerCase().contains("rock"))
                                  .collect(Collectors.toList());

    System.out.println(rockSongs);

    List<Song> beatles = songs.stream()
                              .filter(song -> song.getArtist().contains("Beatles"))     
                              .collect(Collectors.toList());
    System.out.println(beatles);
    
    List<Song> startsWithH = songs.stream()
                                  .filter(song -> song.getTitle().startsWith("H"))
                                  .collect(Collectors.toList());

    System.out.println(startsWithH);                              

}
}




