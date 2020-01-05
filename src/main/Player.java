/**
 * 
 */
package main;
import java.util.ArrayList;

/**
 * <p>Represents a player class. Includes the player's name, the cards in
 * the player's hand (arraylist), and the cards the player has played
 * onto the field (arraylist).</p>
 * @author Leon Wu
 * @version 1.0
 */
public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> field;
    
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        field = new ArrayList<Card>();
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public ArrayList<Card> getField() {
        return field;
    }
    
}
