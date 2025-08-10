public class Song {
private final String title;
private final String artist;
private final String genre;
private final int year;
private final int timesPlayed;

public Song (String title, String artist, String genre, int year, int playCount) {
  this.title = title;
  this.artist = artist;
  this.genre = genre;
  this.year = year;
  this.timesPlayed = playCount;
}

public String getTitle() {
  return title;
}

public String getArtist() {
  return artist;
}

public String getGenre() {
  return genre;
}

public int getYear() {
  return year;
}

public int getTimesPlayed() {
  return timesPlayed;
}

@Override
public String toString() {
  return title + " by " + artist + " a " + genre + " song from " + year + ". This has been played " + timesPlayed + " times.\n";
}
}