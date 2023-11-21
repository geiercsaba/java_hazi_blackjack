package Deck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private Card card;
    @Before
    public void setUp() {
        card = new Card(Suit.CLUBS, Rank.ACE);
    }

    @Test
    public void getSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    public void getRank() {
        assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void getValue() {
        assertEquals(11, card.getRank().getValue());
    }
}