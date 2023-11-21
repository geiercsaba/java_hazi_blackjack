package Game;

/**
 * Player class
 * This class is used to represent the player in the game
 * It extends the Dealer class
 * @see Dealer
 */
public class Player extends Dealer{
    private int money;
    private int currentBet;


    /**
     * Constructor
     * money is set to 1000
     * @param name The name of the player
     */
    public Player(String name)
    {
        super(name);
        this.money = 1000;
        this.currentBet = 0;
    }

    /**
     * Returns the current bet
     */
    public int getCurrentBet()
    {
        return currentBet;
    }

    /**
     * Sets the current bet
     * @param bet The bet to be set
     */
    public void setCurrentBet(int bet)
    {
        currentBet = bet;
    }

    /**
     * Returns the player money
     */
    public int getMoney()
    {
        return money;
    }

    /**
     * Sets the player money
     * @param money The money to be set
     */
    public void setMoney(int money)
    {
        this.money = money;
    }
}
