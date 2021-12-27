package game;

import game.bet.BetType;
import game.cards.Pile;
import game.player.*;

/**
 * This file contains the main program for the Baccarat casino game.
 * It runs the command line with two arguments - the random seed and the
 * number of rounds to play.
 * @author: Rafid Khan
 */

public class Baccarat {
    private static final String BANKER_BETTER = "BankerBetter";
    private static final String CUSTOM_BETTER = "CustomBetter";
    private static final int NUM_PLAYERS = 4;
    private static final String PLAYER_BETTER = "PlayerBetter";
    private static final String TIE_BETTER = "TieBetter";
    private int rounds;
    private double balance = 0;
    private final PlayerBetter playerb;
    private final BankerBetter bankerb;
    private final CustomBetter customb;
    private final TieBetter tieb;
    private final Dealer dealer;
    private final Player[] players;
    public BetType winner;

    public Baccarat(int rounds){
        /*
        Create a new baccarat instance by initializing the players
        and a dealer
         */
        playerb =  new PlayerBetter(PLAYER_BETTER, 0);
        bankerb = new BankerBetter(BANKER_BETTER, 1);
        tieb = new TieBetter(TIE_BETTER, 2);
        customb = new CustomBetter(Baccarat.this, CUSTOM_BETTER, 3);
        players = new Player[]{playerb, bankerb, tieb, customb};
        dealer = new Dealer(this, players);
        this.rounds = rounds;
    }

    public void earn(double amount){
        /*
        The house earns a positive or negative amount of money as the rounds are
        played
         */
        balance += amount;
    }

    public void indicateWinner(BetType winner){
        /*
        Called by the dealer when a winner is determined so the house can adjust the
        win / loss statistics
         */
        this.winner = winner;
    }

    public int getPlayerHandWins(){
        /*
        Returns the number of player hand wins
         */
        return playerb.getWins();
    }

    public int getBankerHandWins(){
        /*
        Returns the number of banker hand wins
         */
        return bankerb.getWins();
    }

    public int getTieHandWins(){
        /*
        Returns the number of tie hand wins
         */
        return tieb.getWins();
    }

    public double getPlayerWinPct(){
        /*
        Returns the player hand win percentage
         */
        return playerb.getWinPercentage();
    }

    public double getBankerWinPct(){
        /*
        Returns the banker hand win percentage
         */
        return bankerb.getWinPercentage();
    }

    public double getTieWinPct(){
        /*
        Returns the tie hand win percentage
         */
        return tieb.getWinPercentage();
    }

    public void playGame(){
        /*
        Plays out each round of the game
         */
        for(int i = 1; i < rounds + 1; i++){
            System.out.println("ROUND " + i);
            dealer.PlayRound();
        }
        System.out.println("STATISTICS");
        System.out.println("\t" + "Rounds played: " + rounds);
        System.out.println("\t" + "Player hand wins: " + getPlayerHandWins());
        System.out.println("\t" + "Banker hand wins: " + getBankerHandWins());
        System.out.println("\t" + "Tie hand wins: " + getTieHandWins());
        System.out.println("\t" + "Player hand win %: " + getPlayerWinPct());
        System.out.println("\t" + "Banker hand win %: " + getBankerWinPct());
        System.out.println("\t" + "Tie hand win %: " + getTieWinPct());
        System.out.println("\t" + "House balance: " + this.balance);
    }

    public static void main(String[] args) {
        /*
        The main program takes the random number generator seed, and the
        number of rounds to play. It also creates a new instance of Baccarat/
         */
        if (args.length != 2){
            System.out.println("Usage: java Baccarat #");
        }
        else{
            Pile.setSeed(Integer.parseInt(args[0]));
            Baccarat start = new Baccarat(Integer.parseInt(args[1]));
            start.playGame();
        }
    }
}