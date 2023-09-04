package com.rslakra.swaggerservice.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @since Aug 08, 2021 15:43:59
 */
public final class Card {

    public enum Rank {
        DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    private static final List<Card> protoDeck = new ArrayList<>();

    // Initialize prototype deck
    static {
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                protoDeck.add(new Card(rank, suit));
    }

    public static ArrayList<Card> newDeck() {
        return new ArrayList<Card>(protoDeck); // Return copy of prototype deck
    }
}
