package Game;

import Deck.*;
import java.util.LinkedList;
import java.util.List;

public class Dealer {

    private LinkedList<Card> hand;
    private String name;

    public Dealer()
    {
        hand = new LinkedList<>();
        name = "Dealer";
    }

    public Dealer(String name)
    {
        hand = new LinkedList<>();
        this.name = name;
    }

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

    public String getName()
    {
        return name;
    }


    public void addCard(Card card)
    {
        hand.add(card);
    }

    public void clearHand()
    {
        hand.clear();
    }

    public List<Card> getHand()
    {
        return hand;
    }

}
