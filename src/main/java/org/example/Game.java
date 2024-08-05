package org.example;

import java.util.Scanner;

public class Game {
    private final Deck deck;
    private final Player player;
    private final Player dealer;

    public Game() {
        deck = new Deck();
        player = new Player(100); // Initial player balance
        dealer = new Player(0);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (player.getBalance() > 0) {
            System.out.println("Your current balance: $" + player.getBalance());
            System.out.print("Enter your bet (increments of 5): ");
            int bet = scanner.nextInt();
            if (bet % 5 != 0 || bet > player.getBalance()) {
                System.out.println("Invalid bet amount.");
                continue;
            }

            // Reset hands
            player.resetHand();
            dealer.resetHand();

            // Place the bet
            player.placeBet(bet);

            // Initial deal
            deck.shuffle();
            player.addCard(deck.dealNextCard());
            player.addCard(deck.dealNextCard());
            dealer.addCard(deck.dealNextCard());
            dealer.addCard(deck.dealNextCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", *]");

            // Player turn
            boolean playerStands = false;
            while (!playerStands && player.calculateHandValue() < 21) {
                System.out.print("Do you want to hit (h), stand (s), or double down (d)? ");
                char action = scanner.next().charAt(0);
                if (action == 'h') {
                    player.addCard(deck.dealNextCard());
                    System.out.println("Your hand: " + player.getHand());
                } else if (action == 's') {
                    playerStands = true;
                } else if (action == 'd' && player.getHand().size() == 2) {
                    player.placeBet(player.getBet() * 2);
                    player.addCard(deck.dealNextCard());
                    System.out.println("Your hand: " + player.getHand());
                    playerStands = true;
                }
            }

            // Dealer turn
            while (dealer.calculateHandValue() < 17) {
                dealer.addCard(deck.dealNextCard());
            }

            // Determine winner
            int playerTotal = player.calculateHandValue();
            int dealerTotal = dealer.calculateHandValue();
            System.out.println("Your hand: " + player.getHand() + " (total: " + playerTotal + ")");
            System.out.println("Dealer's hand: " + dealer.getHand() + " (total: " + dealerTotal + ")");

            if (playerTotal > 21) {
                System.out.println("You bust! You lose.");
                player.loseBet();
            } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
                System.out.println("You win!");
                player.winBet();
            } else if (playerTotal < dealerTotal) {
                System.out.println("Dealer wins! You lose.");
                player.loseBet();
            } else {
                System.out.println("It's a tie!");
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            if (!scanner.next().equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over! Your final balance: $" + player.getBalance());
    }
}
