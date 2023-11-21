package Game;

import Deck.*;
import java.util.LinkedList;

public class Player extends Dealer{
    private int money;
    private int currentBet;


    public Player(String name)
    {
        super(name);
        this.money = 1000;
    }

    public int getCurrentBet()
    {
        return currentBet;
    }

    public void setCurrentBet(int bet)
    {
        currentBet = bet;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }
}
