import java.util.ArrayList;

public class StartupBust {
 private int numOfGuesses = 0; 
 private GameHelper helper = new GameHelper();
 private ArrayList<startups> startups = new ArrayList<Startup>(3);
  
public void setUpGame() {

  Startup byteVillageStartup = new Startup();
  Startup megaTownStartup = new Startup();
  Startup gigaCityStartup = new Startup();

  byteVillageStartup.name = "byteVillage";
  megaTownStartup.name = "megaTown";
  gigaCityStartup.name = "gigaCity";

  startups.add(byteVillageStartup);
  startups.add(megaTownStartup);
  startups.add(gigaCityStartup);

  for (startup startup : startups) {
    int location = helper.placeStartup();
    startup.setLocationCells(location);
  };

  System.out.println("Message here");
}

public void startPlaying() {

  while (startups.size > 0) {
  String guess = System.in.toString("Please make your guess");
    checkUserGuess(guess);
  }
}

public void checkUserGuess(String userGuess) {
  
  numOfGuesses++;
  String result = "miss";


  // Loop through startups and calls each startups 'checkYourself method'
  for (let i = 0; i < startups.size; i ++) {
      if (startups(i).checkYourself(userGuess) == "hit") {
        result = "hit";
        break;
      }
      else if (startups(i).checkYourself(userGuess) == "kill") {
        result = "kill";
        startups.remove(i);
        break;

      }
  }
  System.out.println("Your guess was a: " + result);
}

public void finishGame() {

  String signOff = "chump";
  if (numOfGuesses < 21) {
    signOff = "champ";
  }
  

  System.out.println("You took " + numOfGuesses + " to sink all the ships, " + signOff);
}
}