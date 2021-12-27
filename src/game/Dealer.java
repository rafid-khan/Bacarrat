package game;

import game.bet.Bet;
import game.bet.BetType;
import game.cards.*;
import game.player.*;

import java.util.ArrayList;

/**
 * This file contains a Dealer object which represents the dealer,
 * who is responsible for playing the rounds of the game.
 * @author: Rafid Khan
 */

public class Dealer {
    private static final String DISCARD_FILE_NAME = "Discard";
    private static final int HIT_VALUE = 5;
    private static final int MIN_CARDS_TO_RESHUFFLE = 6;
    private static final String SHOE_PILE_NAME = "Shoe";
    private static final int STAND_VALUE = 8;
    private Pile shoe;
    private ArrayList<Card> shoe_pile;
    private Pile discard;
    private Baccarat house;
    private Player[] players;


    public Dealer(Baccarat house, Player[] players) {
        /*
        Creates the dealer
         */
        this.shoe = new Pile(SHOE_PILE_NAME);
        this.discard = new Pile(DISCARD_FILE_NAME);
        this.house = house;
        this.players = players;

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                this.shoe.addCard(new Card(rank, suit));
            }
        }
        this.shoe.shuffle();
    }

    public void PlayRound() {
        /*
        Plays a single round of Baccarat
         */
        boolean dealer_hits = false;
        boolean player_hits = false;
        boolean stand = false;

        System.out.println("BETS");

        ArrayList<Bet> t = new ArrayList<>();
        for (Player x : players) {
            String z = x.getName() + " bet 1 on " + x.makeBet().getType().desc;
            t.add(x.makeBet());
            System.out.println("\t" + z);
            double amount = x.makeBet().getAmount();
            house.earn(amount);
        }

        if (shoe.numCards() < MIN_CARDS_TO_RESHUFFLE) {
            shoe.addCards(discard);
            shoe.shuffle();
            discard.clearPile(discard);
            System.out.println("\t" +"Reshuffling cards");
        }

        System.out.println("HANDS");
        Hand PlayerHand = new Hand("\tPlayer");
        Hand DealerHand = new Hand("\tDealer");
        PlayerHand.addCard(shoe.drawCard());
        PlayerHand.addCard(shoe.drawCard());
        DealerHand.addCard(shoe.drawCard());
        DealerHand.addCard(shoe.drawCard());
        System.out.println(PlayerHand);
        System.out.println(DealerHand);

        System.out.println("HIT/STAND");
        if (PlayerHand.getValue() >= STAND_VALUE ||
                DealerHand.getValue() >= STAND_VALUE) {
            System.out.println("\tPlayer and Banker stand");
            stand = true;
        }
        if (!stand) {
            if (PlayerHand.getValue() <= HIT_VALUE) {
                System.out.println("\tPlayer hits");
                PlayerHand.addCard(shoe.drawCard());
                player_hits = true;
            }
            if (DealerHand.getValue() <= HIT_VALUE) {
                System.out.println("\tDealer hits");
                DealerHand.addCard(shoe.drawCard());
                dealer_hits = true;
            }
        }
        if ((dealer_hits || player_hits) ||
                (PlayerHand.getValue() > HIT_VALUE && PlayerHand.getValue() < STAND_VALUE)
        && DealerHand.getValue() > HIT_VALUE && DealerHand.getValue() < STAND_VALUE) {
            System.out.println("HANDS");
            System.out.println(PlayerHand);
            System.out.println(DealerHand);
        }

        if (PlayerHand.getValue() == DealerHand.getValue()) {
            house.indicateWinner(BetType.TIE);
        } else if (PlayerHand.getValue() > DealerHand.getValue()) {
            house.indicateWinner(BetType.PLAYER);
        } else {
            house.indicateWinner(BetType.BANKER);
        }

        System.out.println("RESULTS");
        String x = "Winner: " + house.winner.toString() + "\n";
        if (house.winner == BetType.BANKER) {
            String a = "\t" + players[0].getName() + " lost " + players[0].makeBet().getAmount() + "\n" +
                    "\t" + players[1].getName() + " won " + players[1].makeBet().getPayout() + "\n" +
                    "\t" + players[2].getName() + " lost " + players[2].makeBet().getAmount() + "\n";

            players[0].addLoss();
            players[0].adjustBalance(-1);
            players[1].addWin();
            players[1].adjustBalance(players[1].makeBet().getPayout() - 1);
            players[2].addLoss();
            players[2].adjustBalance(-1);
            house.earn(-1 * players[1].makeBet().getPayout());

            if (house.winner == t.get(3).getType()) {
                String b = "\t" + players[3].getName() + " won " + t.get(3).getPayout();
                players[3].addWin();
                players[3].adjustBalance(t.get(3).getPayout() - 1);
                house.earn(- 1 * t.get(3).getPayout());
                System.out.println("\t" + x + a + b);
            } else {
                String b = "\t" + players[3].getName() + " lost " + t.get(3).getAmount();
                players[3].addLoss();
                players[3].adjustBalance(-1);
                System.out.println("\t" + x + a + b);
            }
        } else if (house.winner == BetType.PLAYER) {
            String a = "\t" + players[0].getName() + " won " + players[0].makeBet().getPayout() + "\n" +
                    "\t" + players[1].getName() + " lost " + players[1].makeBet().getAmount() + "\n" +
                    "\t" + players[2].getName() + " lost " + players[2].makeBet().getAmount() + "\n";

            players[0].addWin();
            players[0].adjustBalance(players[0].makeBet().getPayout() - 1);
            players[1].addLoss();
            players[1].adjustBalance(-1);
            players[2].addLoss();
            players[2].adjustBalance(-1);
            house.earn(-1 * players[0].makeBet().getPayout());

            String b;
            if (house.winner == t.get(3).getType()) {
                b = "\t" + players[3].getName() + " won " + t.get(3).getPayout();
                players[3].addWin();
                players[3].adjustBalance(t.get(3).getPayout() - 1);
                house.earn(-t.get(3).getPayout());
            } else {
                b = "\t" + players[3].getName() + " lost " + t.get(3).getAmount();
                players[3].addLoss();
                players[3].adjustBalance(-1);
            }
            System.out.println("\t" + x + a + b);
        } else if (house.winner == BetType.TIE) {
            String a = "\t" + players[0].getName() + " lost " + players[0].makeBet().getAmount() + "\n" +
                    "\t" + players[1].getName() + " lost " + players[1].makeBet().getAmount() + "\n" +
                    "\t" + players[2].getName() + " won " + players[2].makeBet().getPayout() + "\n";

            players[0].addLoss();
            players[0].adjustBalance(-1);
            players[1].addLoss();
            players[1].adjustBalance(-1);
            players[2].addWin();
            players[2].adjustBalance(players[2].makeBet().getPayout() - 1);
            house.earn(-1 * players[2].makeBet().getPayout());

            String b;
            if (house.winner == t.get(3).getType()) {
                b = "\t" + players[3].getName() + " won " + t.get(3).getPayout();
                players[3].addWin();
                players[3].adjustBalance(t.get(3).getPayout() - 1);
                house.earn(-t.get(3).getPayout());
            } else {
                b = "\t" + players[3].getName() + " lost " + t.get(3).getAmount();
                players[3].addLoss();
                players[3].adjustBalance(-1);
            }
            System.out.println("\t" + x + a + b);
        }

        System.out.println("PLAYERS");
        System.out.println(players[0].toString());
        System.out.println(players[1].toString());
        System.out.println(players[2].toString());
        System.out.println(players[3].toString());
        System.out.println();

        for (Card g : PlayerHand.getCards()) {
            discard.addCard(g);
        }
        PlayerHand.clear();

        for (Card h : DealerHand.getCards()) {
            discard.addCard(h);
        }
        DealerHand.clear();

    }
}