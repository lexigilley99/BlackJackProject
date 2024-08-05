package org.example;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Card> hand;
    private int balance;
    private int bet;

    public Player(int balance) {
        this.hand = new ArrayList<>();
        this.balance = balance;
        this.bet = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getBalance() {
        return balance;
    }

    public void placeBet(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    public void winBet() {
        balance += bet;
    }

    public void loseBet() {
        balance -= bet;
    }

    public void resetHand() {
        hand.clear();
    }

    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue().getValue();
            if (card.getValue() == Values.ACE) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }
}
