package Game;

import Deck.Card;
import Deck.Rank;
import Deck.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerPanelTest {
    private PlayerPanel playerPanel;

    @Before
    public void setUp() {
        Player player = new Player("test");
        playerPanel = new PlayerPanel(player);
    }

    @Test
    public void getName() {
        assertEquals("test", playerPanel.getName());
    }

    @Test
    public void updateMoney() {
        playerPanel.updateMoney(100);
        assertEquals(1100, playerPanel.getPlayer().getMoney());
    }

    @Test
    public void addCard() {
        Card card = new Card(Suit.HEARTS, Rank.EIGHT);
        playerPanel.addCard(card);
        assertEquals(8, playerPanel.getPlayer().getHandValue());
    }

    @Test
    public void setSurrender() {
        playerPanel.setSurrender(true);
        assertTrue(playerPanel.isSurrender());
    }

    @Test
    public void getHandValue() {
        assertEquals(0, playerPanel.getPlayer().getHandValue());
    }

    @Test
    public void clearCards() {
        Card card = new Card(Suit.HEARTS, Rank.EIGHT);
        playerPanel.addCard(card);
        playerPanel.clearCards();
        assertEquals(0, playerPanel.getPlayer().getHandValue());
    }

    @Test
    public void getPlayer() {
        assertEquals("test", playerPanel.getPlayer().getName());
    }
}