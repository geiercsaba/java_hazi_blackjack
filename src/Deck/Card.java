package Deck;

/**
 * Card class
 * Represents one card
 * A card has a SUIT and a RANK
 */
public class Card {
    private final Suit suit;
    private final Rank rank;


    /**
     * Card constructor
     * @param suit Suit of the card (HEARTS, DIAMONDS, SPADES, CLUBS)
     * @param rank Rank of the card (ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING)
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get the suit of the card
     * @return Suit of the card (HEARTS, DIAMONDS, SPADES, CLUBS)
     */
    public Suit getSuit()
    {
        return suit;
    }


    /**
     * Get the rank of the card
     * @return Rank of the card ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10)
     */
    public Rank getRank()
    {
        return rank;
    }
}
