package tests.unverified;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

import framework.Test;
import framework.Rules;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.AesculapinumActivator;

/**
 *
 * Test the basic functionality of Aesculapinum
 *
 * @author Damon Stacey
 *
 */

public class CardActivatorAesculapinumBasicTest extends Test {

    @Override
    public String getShortDescription() {
        return "Test the basic functionality of Aesculapinum";
    }

    @Override
    public void run(GameState gameState, MoveMaker move) throws AssertionError,
            UnsupportedOperationException, IllegalArgumentException {
      List<Card> deck = new LinkedList();
      gameState.setDiscard(deck);

      Card[] discs = new Card[8];
      for (int i = 0; i < 8; i++) {
         discs[i] = Card.NOT_A_CARD;
      }
      for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
         gameState.setPlayerCardsOnDiscs(i, discs);
      }   
      
      List<Card> discard = new LinkedList();
      discard.add(Card.AESCULAPINUM);
      discard.add(Card.BASILICA);
      discard.add(Card.CENTURIO);
      discard.add(Card.CONSILIARIUS);
      discard.add(Card.CONSUL);
      discard.add(Card.ESSEDUM);
      discard.add(Card.FORUM);
      discard.add(Card.GLADIATOR);
      discard.add(Card.HARUSPEX);
      discard.add(Card.LEGAT);
      discard.add(Card.LEGIONARIUS);
      gameState.setDiscard(discard);
      discard = gameState.getDiscard();
      
      gameState.setWhoseTurn(0);
      List<Card> hand;
      for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
         gameState.setPlayerSestertii(i, 100);
         gameState.setPlayerVictoryPoints(i, 15);
         hand = new LinkedList();
         hand.add(Card.AESCULAPINUM);
         hand.add(Card.ARCHITECTUS);
         gameState.setPlayerHand(i, hand);
      }
      
      gameState.setActionDice(new int[] {1,1,1});

      move.placeCard(Card.AESCULAPINUM, Rules.DICE_DISC_1);

        //the correct amount of money should be deducted
        //game removed from hand and get placed on the field
      assert(gameState.getPlayerSestertii(0) == 100 - 5);
      assert(gameState.getPlayerHand(0).size() == 1);
      assert(!gameState.getPlayerHand(0).contains(Card.AESCULAPINUM));
      Card[] field;
      field = gameState.getPlayerCardsOnDiscs(0);
      assert(field[0] == Card.AESCULAPINUM);
      
      assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
      assert(!gameState.isGameCompleted());
      
      AesculapinumActivator activator = (AesculapinumActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.AESCULAPINUM));
      assert(gameState.getActionDice().length == 2);
      assert(gameState.getActionDice()[0] == 1);
      assert(gameState.getActionDice()[1] == 1);
      
      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.BASILICA));
      assert(gameState.getActionDice().length == 2);
      assert(gameState.getActionDice()[0] == 1);

      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.CENTURIO));
      assert(gameState.getActionDice() == null || gameState.getActionDice().length == 0);
      
      assert(gameState.getPoolVictoryPoints() == 36 - 15*Rules.NUM_PLAYERS);
      assert(!gameState.isGameCompleted());
      
      move.endTurn();
      assert(gameState.getPlayerVictoryPoints(1) == 9);
      assert(gameState.getPlayerSestertii(1) == 100);
      assert(gameState.getPlayerHand(0).contains(Card.AESCULAPINUM));
      assert(!gameState.getPlayerHand(0).contains(Card.BASILICA));
      assert(!gameState.getPlayerHand(0).contains(Card.CENTURIO));
      
      move.endTurn();
      assert(gameState.getPlayerVictoryPoints(1) == 10);
      assert(gameState.getPlayerSestertii(0) == 100 - 5);
      assert(gameState.getPlayerHand(0).contains(Card.AESCULAPINUM));
      assert(gameState.getPlayerHand(0).contains(Card.BASILICA));
      assert(gameState.getPlayerHand(0).contains(Card.CENTURIO));

      gameState.setActionDice(new int[] {1,1,1});
      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.CONSILIARIUS));
      assert(gameState.getActionDice().length == 2);
      assert(gameState.getActionDice()[0] == 1);
      assert(gameState.getActionDice()[1] == 1);
      
      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.CONSUL));
      assert(gameState.getActionDice().length == 2);
      assert(gameState.getActionDice()[0] == 1);

      activator.chooseCardFromPile(1);
      activator.complete();
      assert(gameState.getPlayerHand(0).contains(Card.ESSEDUM));
      assert(gameState.getActionDice() == null || gameState.getActionDice().length == 0);
      assert(!gameState.isGameCompleted());

    }
}
