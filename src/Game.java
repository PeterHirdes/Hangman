import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

  String mysteryWord = "Uninitialized";

  /**
   * Get list of words from file
   * @return list of words
   */
  public static List<String> readFile(){
    List<String> words = new ArrayList<>();

    //Wanneer de class waarin je werkt niet static is kun je "getClass().getClassLoader..." gebruiken
    //Omdat de Main class static is benaderen we getClassLoader ook static. "Main.class" is een statische verwijziging naar de huidige class
    try(InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(
        "Woordlijst.txt")){
      Scanner dictScanner = new Scanner(inputStream);
      while (dictScanner.hasNextLine()) {
        words.add(dictScanner.nextLine());
      }
      //niet vergeten de input stream weer te sluiten
      dictScanner.close();
    }catch(IOException e){
      //Hier moeten we eigenlijk netjes de IOException afhandelen.
      //als het bestand niet gevonden of uitgelezen kan worden is het spel spelen niet mogeljk
    }

    return words;
  }

  void SetMysteryWord(){

  }
}
