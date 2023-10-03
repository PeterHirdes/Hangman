import java.util.Scanner;

public class GameSystem {


  public void playGame()
  {
    Game game1 = new Game("Filantroop");

    Scanner myInput = new Scanner(System.in);
    while(!game1.checkAllLettersGuessed() & game1.numberOfMistakes < 10){
      game1.takeTurn(myInput);
    }
    }
  }

