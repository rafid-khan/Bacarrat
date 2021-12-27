package test;

import game.cards.Card;
import game.cards.Rank;
import game.cards.Suit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * JUnit test framework for the Card class.
 *
 * @author RIT CS
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCard {
    @Test
    public void test1_getValue() {
        Card c1 = new Card(Rank.TEN, Suit.SPADE);
        Card c2 = new Card(Rank.QUEEN, Suit.DIAMOND);
        Card c3 = new Card(Rank.NINE, Suit.SPADE);
        Card c4 = new Card(Rank.FOUR, Suit.HEART);
        Card c5 = new Card(Rank.ACE, Suit.CLUB);

        assertEquals(0, c1.getValue());
        assertEquals(0, c2.getValue());
        assertEquals(9, c3.getValue());
        assertEquals(4, c4.getValue());
        assertEquals(1, c5.getValue());
    }

    @Test
    public void test2_toString() {
        Card c1 = new Card(Rank.THREE, Suit.CLUB);
        Card c2 = new Card(Rank.TEN, Suit.DIAMOND);
        Card c3 = new Card(Rank.QUEEN, Suit.HEART);
        Card c4 = new Card(Rank.ACE, Suit.SPADE);

        assertEquals("3♧", c1.toString());
        assertEquals("10♢", c2.toString());
        assertEquals("Q♥", c3.toString());
        assertEquals("A♠", c4.toString());
    }

    @Test
    public void test3_equals() {
        Card c1 = new Card(Rank.ACE, Suit.SPADE);
        Card c2 = new Card(Rank.FIVE, Suit.DIAMOND);
        Card c3 = new Card(Rank.ACE, Suit.CLUB);

        assertEquals(c1, c1);
        assertNotEquals(c1, c2);
        assertEquals(c1, c3);
        assertNotEquals(c1, "A♣");
        assertNotEquals("A♣", c1);
    }
}
