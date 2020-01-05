/**
 * 
 */
package main;

/**
 * <p>Represents the deck of all possible cards in an array.</p>
 * @author Leon Wu
 * @version 1.0
 */
public class Deck {

    private Card[] allCards;
    
    public Deck() {
        allCards = new Card[72];
        for (int i = 0; i < 12; i++) {
            allCards[i] = new Card("EggRoll");
        }
        for (int i = 12; i < 24; i++) {
            allCards[i] = new Card("SalmonRoll");
        }
        for (int i = 24; i < 36; i++) {
            allCards[i] = new Card("SquidRoll");
        }
        for (int i = 36; i < 48; i++) {
            allCards[i] = new Card("Maki");
        }
        for (int i = 48; i < 56; i++) {
            allCards[i] = new Card("Sashimi");
        }
        for (int i = 56; i < 64; i++) {
            allCards[i] = new Card("Dumplings");
        }
        for (int i = 64; i < 72; i++) {
            allCards[i] = new Card("Tofu");
        }
    }
    
    public Card getCard(int index) {
        return allCards[index];
    }
    
}
