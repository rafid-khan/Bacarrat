package game.bet;

/**
 * This file contains a Bet object which represents a single bet
 * by a player
 * @author: Rafid Khan
 */
public class Bet {
    private int playerID;
    private BetType type;
    private int amount;

    public Bet(int playerID, BetType type, int amount){
        /*
        Creates a new bet
         */
        this.playerID = playerID;
        this.type = type;
        this.amount = amount;
    }

    public BetType getType(){
        /*
        Returns the bet type
         */
        return this.type;
    }

    public int getAmount(){
        /*
        Returns the bet amount
         */
        return this.amount;
    }

    public double getPayout(){
        /*
        Returns the payout based on the
        amount
         */
        double payout;
        if (this.type == BetType.PLAYER){
            payout = this.amount * 2;
        }
        else if (this.type == BetType.BANKER){
            payout = this.amount * 1.95;
        }
        else{
            payout = this.amount * 9;
        }
        return payout;
    }

    @Override
    public String toString(){
        /*
        Returns a string representation of the bet including the player ID,
        type, and amount
         */
        return this.playerID + this.type.toString() + this.amount;
    }

}
