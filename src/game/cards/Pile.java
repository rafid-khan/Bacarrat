package game.cards;
import java.util.*;
/**
 * This file contains the Pile class which represents the pile of cards that
 * are dealt out to the hands.
 * @author : Rafid Khan
 */

public class Pile {
    private String name;
    private static Random rng = new Random();
    private ArrayList<Card> pile;

    public Pile(String name){
        /*
        Creates an empty Pile object
         */
        this.name = name;
        this.pile = new ArrayList<>();
    }

    public static void setSeed(long seed){
        /*
        Seed is based on a random number generator
         */
        rng = new Random();
        rng.setSeed(seed);
    }

    public void addCard(Card card){
        /*
        Adds a Card Object to the back of the Pile Object
         */
        this.pile.add(card);
    }

    public void clearPile(Pile pile){
        /*
        Clears the Pile of all cards
         */
        this.pile.clear();
    }

    public void addCards(Pile pile){
        /*
        Adds a collection of cards from one pile into another
         */
        this.pile.addAll(pile.pile);
    }

    public Card drawCard(){
        /*
        Removes the front card from the pile
         */
        return this.pile.remove(0);
    }

    public int numCards(){
        /*
        Returns the number of cards that are in the pile
         */
        return this.pile.size();
    }

    public void shuffle(){
        /*
        Shuffles the cards using the random number generator
         */
        Collections.shuffle(pile, rng);
    }

    @Override
    public String toString(){
        /*
        Returns a string representation of the pile of cards
         */
        String result = "Test pile: ";
        for(Card x : this.pile){
            result += x.toString() + " ";
        }
        return result;
    }
}
