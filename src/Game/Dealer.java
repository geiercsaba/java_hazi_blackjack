package Game;

import Deck.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Dealer class represents the dealer in a game of blackjack.
 * The dealer has a hand of cards and a name.
 * The dealer can add cards to their hand, clear their hand, and get the value of their hand.
 * The dealer can also get their name and their hand.
 */
public class Dealer {

    private LinkedList<Card> hand;
    private String name;

    /**
     * The dealer's hand is initialized as an empty linked list.
     * The dealer's name is set to "Dealer".
     */
    public Dealer()
    {
        hand = new LinkedList<>();
        name = "Dealer";
    }

    /**
     * The dealer's hand is initialized as an empty linked list.
     * The dealer's name is set to the given name.
     * @param name The name of the dealer.
     */
    public Dealer(String name)
    {
        hand = new LinkedList<>();
        this.name = name;
    }

    /**
     * The value of the dealer's hand is calculated.
     * The value of the dealer's hand is the sum of the values of the cards in the hand (aces are 1/11).
     * @return The value of the dealer's hand.
     */
    public int getHandValue()
    {

        int value = 0;
        int ace = 0;
        for(Card card : hand)
        {
            value += card.getRank().getValue();
            if(card.getRank() == Rank.ACE)
            {
                ace++;
            }
        }
        while(value > 21 && ace > 0)
        {
            value -= 10;
            ace--;
        }

        return value;
    }


    /**
     * The name of the dealer is returned.
     * @return The name of the dealer.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds the given card to the dealer's hand.
     * @param card The card to be added to the dealer's hand.
     */
    public void addCard(Card card)
    {
        hand.add(card);
    }

    /**
     * Clears the dealer's hand.
     */
    public void clearHand()
    {
        hand.clear();
    }


    /**
     * Returns the dealer's hand.
     * @return The dealer's hand.
     */
    public List<Card> getHand()
    {
        return hand;
    }

}
