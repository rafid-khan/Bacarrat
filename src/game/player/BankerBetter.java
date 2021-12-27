package game.player;
/**
 * This file contains the BankerBetter Player
 * This player always bets on the banker hand
 * @author: Rafid Khan
 */

import game.bet.Bet;
import game.bet.BetType;

public class BankerBetter extends Player{
    private String name;
    private int id;

    public BankerBetter(String name, int id){
        /*
        Creates the BankerBetter
         */
        super("BankerBetter", 1);
    }

    public Bet makeBet(){
        /*
        Always bets on the banker hand
         */
        return new Bet(this.id, BetType.BANKER, 1);
    }
}
