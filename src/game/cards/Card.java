package game.cards;
/**
 * This file contains the Card object which represents
 * a single card in the game
 * @author : Rafid Khan
 */
public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit){
        /*
        Creates the card with a rank and suit
         */
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        /*
        Returns an integer value based
        on the card's rank
         */
        return this.rank.getValue();
    }

    public String toString(){
        /*
        Returns a string representation of the card,
        including rank and suit
         */
        return this.rank.toString() + this.suit.toString();
    }

    public boolean equals(Object other) {
        /*
        Two cards are equal if they have the same rank
        Compares two cards' ranks
         */
        if (other instanceof Card) {
            return this.rank.equals(((Card) other).rank);
        }
        else{
            return false;
        }
    }
}
