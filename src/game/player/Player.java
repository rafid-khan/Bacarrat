package game.player;

/**
 * This file contains the abstract Player class which
 * represents a player / better in the game
 * @author: Rafid Khan
 */

import game.bet.Bet;

public abstract class Player {

    private String name;
    protected int id;
    private int wins = 0;
    private int losses = 0;
    private double balance = 0;

    public Player(String name, int id){
        /*
        Create a new player
         */
        this.name = name;
        this.id = id;
    }

    public String getName(){
        /*
        Gets the player's name
         */
        return this.name;
    }

    public int getID(){
        /*
        Get's the player's ID
         */
        return this.id;
    }

    public double getWinPercentage(){
        /*
        Gets the win percentage of the player over the course
        of all bets
         */
        double total = wins + losses;
        if (wins == 0){
            return 0;
        }
        else{
            return wins / total;
        }
    }

    public void addWin() {
        /*
        Player won their bet
         */
        wins += 1;
    }

    public int getWins(){
        /*
        Returns how many wins a player has
         */
        return wins;
    }

    public void addLoss(){
        /*
        Player lost their bet
         */
        losses += 1;
    }

    public double getBalance(){
        /*
        Gets the current balance of the player
         */
        return balance;
    }

    public void adjustBalance(double amount){
        /*
        Adjusts the player's balance by positive / negative
        amount
         */
        balance += amount;
    }

    public abstract Bet makeBet();
    /*
    The player makes their own bet based on their playing style
     */

    public String toString(){
        /*
        Returns a string representation of the player
         */
        String x = "\tPlayer{name='" + this.name + "', " + "id=" + this.id + ", "
                + "balance=" + this.balance + ", " + "wins=" + this.wins + ", " +
                "losses=" + this.losses + ", " + "win percentage=" + this.getWinPercentage() +
                "}";
        return x;
    }
}


