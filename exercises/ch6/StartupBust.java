import java.util.ArrayList;

public class StartupBust {
 private int numOfGuesses = 0; 
 private GameHelper helper = new GameHelper();
 private ArrayList<startups> startups = new ArrayList<Startup>(3);
  
private void setUpGame() {

  Startup one = new Startup();
  Startup two = new Startup();
  Startup three = new Startup();

  one.setName("poniez");
  two.setName("hacqi");
  three.setName("cabista");

  startups.add(one);
  startups.add(two);
  startups.add(three);

  System.out.println("Your goal is to sink three startups");
  System.out.println("poniez, hacqi, and cabista");
  System.out.println("Try and sink them in as few guesses as possible");
  
  
  for (Startup startup : startups) {
    ArrayList<String> newLocation = helper.placeStartup(3);
    startup.setLocationCells(newLocation);
  };
}

private void startPlaying() {

  while (!startups.isEmpty()) {
  String userGuess = helper.getUserInput("Please make your guess");
    checkUserGuess(userGuess);
  }
  finishGame();
}

private void checkUserGuess(String userGuess) {
  
  numOfGuesses++;
  String result = "miss";


  // Loop through startups and calls each startups 'checkYourself method'
  for (Startup startupToTest : startups) {
      result = startupToTest.checkYourself(userGuess);
      if (result == "hit") {
        break;
      }
      else if (result == "kill") {
        startups.remove(startupToTest);
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

public static void main(String[] args) {
  StartupBust game = new StartupBust();
  game.setUpGame();
  game.startPlaying();
}

}