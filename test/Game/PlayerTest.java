package Game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    @Before
    public void setUp() {
        player = new Player("test");
    }

    @Test
    public void getName() {
        assertEquals("test", player.getName());
    }

    @Test
    public void getCurrentBet() {
        assertEquals(0, player.getCurrentBet());
    }

    @Test
    public void setCurrentBet() {
        player.setCurrentBet(500);
        assertEquals(500, player.getCurrentBet());
    }

    @Test
    public void getMoney() {
        assertEquals(1000, player.getMoney());
    }

    @Test
    public void setMoney() {
        player.setMoney(500);
        assertEquals(500, player.getMoney());
    }
}