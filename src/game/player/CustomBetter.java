package game.player;

/**
 This file contains the CustomBetter Player
 * This player bets based on whichever hand is the furthest below the
 * expected winning percentages
 * @author: Rafid Khan
 */


import game.Baccarat;
import game.bet.Bet;
import game.bet.BetType;

public class CustomBetter extends Player{
    private static final double BANKER_WIN_PCT = 0.4585;
    private static final double PLAYER_WIN_PCT = 0.4462;
    private static final double TIE_WIN_PCT = 0.0953;
    Baccarat house;

    public CustomBetter(Baccarat house, String name, int id) {
        /*
        Creates the CustomBetter
         */
        super(name, id);
        this.house = house;
    }

    public Bet makeBet() {
        /*
        Makes the bet based on the which win percentage is the lowest
        from the expected win percentage
         */
        double banker_difference = BANKER_WIN_PCT - house.getBankerWinPct();
        double player_difference = PLAYER_WIN_PCT - house.getPlayerWinPct();
        double tie_difference = TIE_WIN_PCT - house.getTieWinPct();

        if ((banker_difference) > player_difference &&
                banker_difference > tie_difference){
            return new Bet(this.id, BetType.BANKER, 1);
        }
        else if (player_difference > banker_difference &&
                player_difference > tie_difference){
            return new Bet(this.id, BetType.PLAYER, 1);
        }
        else{
            return new Bet(this.id, BetType.TIE, 1);
        }
    }
}
