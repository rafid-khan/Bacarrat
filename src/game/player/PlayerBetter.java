package game.player;

import game.bet.Bet;
import game.bet.BetType;

/**
 This file contains the PlayerBetter Player
 * This player always bets on the player hand
 * @author: Rafid Khan
 */

public class PlayerBetter extends Player{
    private String name;
    private int id;

    public PlayerBetter(String name, int id){
        /*
        Creates the PlayerBetter
         */
        super(name, 0);
    }

    public Bet makeBet(){
        /*
        Always bets on the player hand
         */
        return new Bet(this.id, BetType.PLAYER, 1);
    }
}
