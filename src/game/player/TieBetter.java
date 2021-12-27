package game.player;

import game.bet.Bet;
import game.bet.BetType;

/**
 This file contains the TieBetter Player
 * This player always bets on the tie hand
 * @author: Rafid Khan
 */


public class TieBetter extends Player{
    private String name;
    private int id;

    public TieBetter(String name, int id){
        /*
        Creates the TieBetter
         */
        super("TieBetter", 2);
    }

    public Bet makeBet(){
        /*
        Always bets on the tie hand
         */
        return new Bet(this.id, BetType.TIE, 1);
    }
}
