package Game;

import Deck.Card;
import Deck.Rank;
import Deck.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealerPanelTest {
    private DealerPanel dealerPanel;

    @Before
    public void setUp() {
        dealerPanel = new DealerPanel(new Dealer());
    }

    @Test
    public void addCard() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        dealerPanel.addCard(card);
        assertEquals(1, dealerPanel.getDealer().getHand().size());
    }

    @Test
    public void getCardValue() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        dealerPanel.addCard(card);
        assertEquals(11, dealerPanel.getCardValue());
    }

    @Test
    public void clearCards() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        dealerPanel.addCard(card);
        dealerPanel.clearCards();
        assertEquals(0, dealerPanel.getDealer().getHand().size());
    }

    @Test
    public void getDealer() {
        assertEquals(Dealer.class, dealerPanel.getDealer().getClass());
    }
}