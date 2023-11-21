package Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Deck class represents a deck of cards.
 * It contains a list of cards and methods to initialize the deck and get a card from the deck.
 * The deck is initialized with 52 cards, one of each suit and rank.
 * The deck is shuffled after initialization.
 */
public class Deck {
    private List<Card> cards;

    /**
     * Initializes the deck with 52 cards, one of each suit and rank.
     * Shuffles the deck after initialization.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(cards);
    }

    /**
     * Initializes the deck with 52 cards, one of each suit and rank.
     */
    private void initializeDeck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Removes the first card from the deck and returns it.
     * @return Card from the deck.
     */
    public Card getCard() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
