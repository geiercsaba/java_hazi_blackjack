package Game;

import Deck.Card;
import Deck.Rank;
import Deck.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealerTest {
    private Dealer dealer;
    @Before
    public void setUp() {
        dealer = new Dealer();
    }

    @Test
    public void getHandValue() {
        assertEquals(0, dealer.getHandValue());
    }

    @Test
    public void getName() {
        assertEquals("Dealer", dealer.getName());
    }

    @Test
    public void addCard() {
        Card card1 = new Card(Suit.CLUBS, Rank.ACE);
        Card card2 = new Card(Suit.DIAMONDS, Rank.KING);
        dealer.addCard(card1);
        dealer.addCard(card2);
        assertEquals(21, dealer.getHandValue());
    }

    @Test
    public void clearHand() {
        Card card1 = new Card(Suit.CLUBS, Rank.ACE);
        Card card2 = new Card(Suit.DIAMONDS, Rank.KING);
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.clearHand();
        assertEquals(0, dealer.getHandValue());
    }

    @Test
    public void getHand() {
        Card card1 = new Card(Suit.CLUBS, Rank.ACE);
        Card card2 = new Card(Suit.DIAMONDS, Rank.KING);
        dealer.addCard(card1);
        dealer.addCard(card2);
        assertEquals(2, dealer.getHand().size());
    }
}