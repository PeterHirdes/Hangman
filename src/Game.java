import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Game {

  static String mysteryWord = "Uninitialized";
  static String guessedLetters = "";
  static int numberOfMistakes = 0;
  static String hiddenWord = "";
  static boolean hardMode = false;
  public Game(String word){
    setMysteryWord(word);
  }

  public void setMysteryWord(String word){
    mysteryWord = word.toLowerCase();
  }

  public void createHiddenWord() {
    hiddenWord = "";
    for (int i = 0; i < mysteryWord.length(); i++) {
      if (guessedLetters.contains(Character.toString(mysteryWord.charAt(i)))) {
        hiddenWord += Character.toUpperCase(mysteryWord.charAt(i));
      } else {
        hiddenWord += ".";
      }
    }
  }

  public void takeGuess(String guess){
    guess = guess.toLowerCase(); // met testen is dit nodig,
    createHiddenWord();
    if (isLetterInWord(guess)) {
      if (!isLetterAlreadyGuessed(guess)) {
        guessedLetters += guess;
        createHiddenWord();
        goodGuessNewLetter(guess);
      } else {
        goodGuessRepeatedLetter(guess);
      }
    }
    else {
      if (!isLetterAlreadyGuessed(guess)){
        guessedLetters += guess;
        numberOfMistakes += 1;
        wrongGuessNewLetter(guess);
      }
      else {
        wrongGuessRepeatedLetter(guess);
        if (hardMode){
          numberOfMistakes += 1;
        }
      }
    }
  }

  public void goodGuessRepeatedLetter(String guess){
    System.out.println("Je hebt " + guess.toUpperCase() + " al gegokt. Probeer een nieuwe letter.");
    System.out.println("Dit is je situatie: " + String.join(" ", hiddenWord.split("")));
    if (!hardMode) {
      System.out.println(
          "Hier is het overzicht van je gegokte letters : " + getGuessedLettersOverview());
    }
    else {
      numberOfMistakes += 1;
    }
  }

  public void goodGuessNewLetter(String guess){
    System.out.println("Goed gegokt! " + guess.toUpperCase() + " zit in het woord.");
    System.out.println("Dit is je situatie: " + String.join(" ", hiddenWord.split("")));
    System.out.println(generateHangman());
    if (!hardMode) {
      System.out.println(
          "Hier is het overzicht van je gegokte letters : " + getGuessedLettersOverview());
    }
  }

  public void wrongGuessNewLetter(String guess){
    System.out.println("Helaas! " + guess.toUpperCase() + " zit niet in het woord.");
    System.out.println(generateHangman());
    System.out.println("Hier is het overzicht van je gegokte letters: " + getGuessedLettersOverview());
  }
  public void wrongGuessRepeatedLetter(String guess){
    System.out.println("Je hebt " + guess.toUpperCase() + " al gegokt. Probeer een nieuwe letter.");
    System.out.println("Dit is je situatie: " + String.join(" ", hiddenWord.split("")));
    if (!hardMode){
      System.out.println("Hier is het overzicht van je gegokte letters : " + getGuessedLettersOverview());
    }
  }

  public String getGuessedLettersOverview()
  {

    if (!guessedLetters.isEmpty()) {
      return "[" + String.join(", ", guessedLetters.split("")).toUpperCase() + "]";
    }
    else {
      return guessedLetters; // dit kan eigenlijk niet gebeuren, deze method wordt altijd pas aangeroepen nadat tenminste 1 letter is gegokt
    }
  }
  public void takeTurn(Scanner input){
    if (guessedLetters.isEmpty()){
      startMessage();
    }
    System.out.println("Typ een enkele letter om mee te raden: ");
    String guess = input.nextLine();
    while(!checkInput(guess)){
      System.out.println("Verkeerde Input[!] - > Probeer nogmaals een enkele letter in te typen: ");
      guess = input.nextLine();
    }
    guess = guess.toLowerCase();
    takeGuess(guess);

    if (checkAllLettersGuessed() || numberOfMistakes == 10)
      endMessage();

  }

  public void startMessage(){
    createHiddenWord();
    System.out.println("Welkom bij galgje! Het woord dat je moet raden is: " + String.join(" ", hiddenWord.split("")));
    System.out.println("Als je het woordt raadt voordat je 10 foute letters hebt gegokt win je. Succes!");
  }
  public boolean isLetterInWord(String letter){
    return mysteryWord.contains(letter.toLowerCase());
  }

  public boolean isLetterAlreadyGuessed(String letter){
    return guessedLetters.contains(letter.toLowerCase());
  }

  public boolean checkAllLettersGuessed(){
    return (hiddenWord.toLowerCase().equals(mysteryWord));
  }
  public boolean checkInput(String input){
    if (input.isEmpty()){
      System.out.println("Je hebt niets ingetypt!");
      return false;
    }
    else if (input.length() > 1){
      System.out.println("Je hebt meer dan 1 teken ingetypt!");
      return false;
    }
    else if (Character.isAlphabetic(input.charAt(0))){
      System.out.println("Je hebt een '" + Character.toUpperCase(input.charAt(0)) + "' ingevuld.");
      return true;
    }
    else {
      System.out.println("Je hebt geen letter ingetypt.");
      return false;
    }
  }

  public static String generateHangman() {
    String hangman = "";
    if (numberOfMistakes == 0) {
      hangman=""; }
    else if (numberOfMistakes == 1) {
      hangman = "|\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes == 2) {
      hangman = "____\n|\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes == 3) {
      hangman = "____\n|/\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes == 4) {
      hangman = "____\n|/ |\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes == 5) {
      hangman = "____\n|/ |\n|  O\n|\n|\n|_ _"; }
    else if (numberOfMistakes == 6) {
      hangman = "____\n|/ |\n|  O\n|  |\n|\n|_ _"; }
    else if (numberOfMistakes == 7) {
      hangman = "____\n|/ |\n|  O\n| /|\n|\n|_ _"; }
    else if (numberOfMistakes == 8) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n|\n|_ _"; }
    else if (numberOfMistakes == 9) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n| /\n|_ _"; }
    else if (numberOfMistakes == 10) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n| / \\\n|_ _"; }

    return hangman;
  }

  public void endMessage() {
    if (checkAllLettersGuessed()) {
      System.out.println("Gefeliciteerd! Je hebt het woord geraden!");
    }
    if (numberOfMistakes == 10) {
      System.out.println("Helaas, je hebt het woord niet geraden. Het te raden woord was: " + mysteryWord.toUpperCase());
    }
  }
}
