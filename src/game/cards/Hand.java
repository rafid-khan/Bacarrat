package game.cards;
/**
 * This file contains the Hand class which represents a hand in the game
 * (either the player or banker)
 * @author : Rafid Khan
 */

import java.util.ArrayList;
import java.util.Collection;

public class Hand implements Comparable<Hand>{
    private String name;
    private ArrayList<Card> hand;

    public Hand(String name){
        /*
        Creates a new empty hand
         */
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card){
        /*
        Adds a card object to the hand
         */
        this.hand.add(card);
    }

    public Collection<Card> getCards(){
        /*
        Gets the collection of cards in the hand
         */
        return this.hand;
    }

    public void clear(){
        /*
        Clears the hand out by removing all the cards
         */
        this.hand.clear();
    }

    public int getValue(){
        /*
        Gets the value of the hand by (sum modulo 10)
         */
        int y = 0;
        for(Card x : hand){
            y += x.getValue();
        }
        return y % 10;
    }

    public int compareTo(Hand other){
        /*
        Compares this hand's value with another hand's value
         */
        return this.getValue() - other.getValue();
    }

    @Override
    public String toString(){
        /*
        Returns a string representation of a Hand Object
         */
        StringBuilder result = new StringBuilder(this.name + " (" + this.getValue() + ")" + ": ");
        for(Card x : hand){
            result.append(x.toString()).append(" ");
        }
        return result.toString();
    }
}
