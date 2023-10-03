import org.junit.Assert;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

  private Game game1;
  @BeforeEach
  void setup(){
    game1 = new Game("appel");
  }

  @Test
  void checkMysteryWordIfSet(){
    Assert.assertTrue(game1.mysteryWord.equals("appel"));
  }
  @Test
  void checkIfLetterInWord() {
    Assert.assertTrue(game1.isLetterInWord("a"));
    Assert.assertFalse(game1.isLetterInWord("b"));
  }
  @Test
  void checkIfLetterAlreadyGuessed() {
    Assert.assertFalse(game1.isLetterAlreadyGuessed("a"));
    Assert.assertFalse(game1.isLetterAlreadyGuessed("A"));
    game1.takeGuess("A");
    Assert.assertTrue(game1.isLetterAlreadyGuessed("a"));
    Assert.assertTrue(game1.isLetterAlreadyGuessed("A"));
  }

  @Test
  void checkIfGameisWon() {
    game1.takeGuess("A");
    game1.takeGuess("P");
    game1.takeGuess("E");
    Assert.assertFalse(game1.hiddenWord.toLowerCase().equals(game1.mysteryWord));
    game1.takeGuess("L");
    // Ik heb geen gamestate om te checken... dus deze test is niet heel nuttig
    Assert.assertTrue(game1.hiddenWord.toLowerCase().equals(game1.mysteryWord));
  }
  @Test
  void checkIfGameislost() {
    //zelfde als hierboven, ik heb geen gamestate, dus beetje omslachtige en niet heel nuttige test
    game1.takeGuess("b");
    Assert.assertTrue(game1.numberOfMistakes == 1);
    game1.takeGuess("c");
    Assert.assertTrue(game1.numberOfMistakes == 2);
    game1.takeGuess("d");
    Assert.assertTrue(game1.numberOfMistakes == 3);
    game1.takeGuess("e");
    Assert.assertTrue(game1.numberOfMistakes == 3);
    game1.takeGuess("f");
    Assert.assertTrue(game1.numberOfMistakes == 4);
    game1.takeGuess("g");
    Assert.assertTrue(game1.numberOfMistakes == 5);
    game1.takeGuess("h");
    Assert.assertTrue(game1.numberOfMistakes == 6);
    game1.takeGuess("i");
    Assert.assertTrue(game1.numberOfMistakes == 7);
    game1.takeGuess("j");
    Assert.assertTrue(game1.numberOfMistakes == 8);
    game1.takeGuess("k");
    Assert.assertTrue(game1.numberOfMistakes == 9);
    game1.takeGuess("l");
    Assert.assertTrue(game1.numberOfMistakes == 9);
    game1.takeGuess("m");
    Assert.assertTrue(game1.numberOfMistakes == 10);
  }
}