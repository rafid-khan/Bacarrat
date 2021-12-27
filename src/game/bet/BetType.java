package game.bet;
/**
 * This file contains an enum to represent the different types of
 * bets the players can make
 * @author: Rafid Khan
 */

public enum BetType {
    BANKER("BANKER", 1.95),
    PLAYER("PLAYER", 2),
    TIE("TIE", 9);

    public final String desc;
    public final double multiplier;

    BetType(final String description, double multiplier) {
        /*
        Initializes "Enhanced" Enum
         */
        this.desc = description;
        this.multiplier = multiplier;
    }

    public double getPayout(double amount) {
        /*
        Gets the payout for the bet based on the amount
         */
        double payout;
        if (BetType.BANKER.toString().equals("BANKER")) {
            payout = this.multiplier * amount;
        } else if (BetType.PLAYER.toString().equals("PLAYER")) {
            payout = this.multiplier * amount;
        } else if (BetType.TIE.toString().equals("TIE")){
            payout = this.multiplier * amount;
        }
        else{
            return 0;
        }
        return payout;
    }
}
