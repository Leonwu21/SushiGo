/**
 * 
 */
package main;

/**
 * <p>Represents a card to be played. Includes the card's type as a string.</p>
 * @author Leon Wu
 * @version 1.0
 */
public class Card {
    private String type;

    public Card(String input) {
        type = input;
    }
    
    public String getType() {
        return type;
    }
}
