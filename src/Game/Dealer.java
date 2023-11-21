package Game;

import Deck.*;
import java.util.LinkedList;
import java.util.List;

public class Dealer {

    private LinkedList<Card> Hand;
    private String name;

    public Dealer()
    {
        Hand = new LinkedList<>();
        name = "Dealer";
    }

    public Dealer(String name)
    {
        Hand = new LinkedList<>();
        this.name = name;
    }

    public int getHandValue()
    {

        int value = 0;
        int ace = 0;
        for(Card card : Hand)
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
        Hand.add(card);
    }

    public void clearHand()
    {
        Hand.clear();
    }

    public LinkedList<Card> getHand()
    {
        return Hand;
    }

}
