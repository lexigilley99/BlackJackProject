package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Collections;

public class Deck implements DeckActions {

    private final ArrayList<Card> myCards;
    private int numCards;

    public Deck() {
        myCards = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            for (Values value : Values.values()) {
                myCards.add(new Card(suit, value));
            }
        }
        numCards = myCards.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(myCards);
    }

    @Override
    public Card dealNextCard() {
        if (numCards == 0) {
            return null; // No cards left to deal
        }
        numCards--;
        return myCards.remove(0);
    }

    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint && i < numCards; i++) {
            System.out.println(myCards.get(i));
        }
    }

    public int cardsLeft() {
        return numCards;
    }
}