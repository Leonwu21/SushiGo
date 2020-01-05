/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Drives the main game.</p>
 * 
 * <p>Updates to be included in the future:
 * <ul><li>GUI</li>
 * <li>More cards</li>
 * <li>Option to play using different starting cards</li></ul></p>
 * 
 * @author Leon Wu
 * @version 1.0
 */
public class RunGame {
    
    // Stores cards drawn from deck in an array list, avoiding redrawing.
    static ArrayList<Integer> drawn = new ArrayList<Integer>();
    
    // Creates the array of all possible cards.
    static Deck deck = new Deck(); 
    
    // Creates the scanner object for user input.
    static Scanner scan = new Scanner(System.in);
    /**
     * <p>Drives the program.</p>
     * @param args unused
     */
    public static void main(String[] args) {
        tutorial();
        
        //Scan in players
        
        System.out.println("Enter name of Player 1:");
        String user1 = scan.nextLine();
        System.out.println("Enter name of Player 2:");
        String user2 = scan.nextLine();
        
        // Instantiate Players
        Player player1 = new Player(user1);
        Player player2 = new Player(user2);
        
        // Mnstantiate hands -> cards
        deal(player1);
        deal(player2);
        declareHand(player1);
        declareHand(player2);
        System.out.println();
        
        // Main "playing" loop
        while (player2.getHand().size() != 0) {
            play(player1);
            System.out.println();
            play(player2);
            swap(player1, player2);
        }
        
        // Calculate scores at end of round
        if (calculateScores(player1, player2) > calculateScores(player2, player1)) {
            System.out.println(user1 + " wins!");
        } else if (calculateScores(player1, player2) < calculateScores(player2, player1)) {
            System.out.println(user2 + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
        
        System.out.println(user1 + "'s score: " + calculateScores(player1, player2));
        System.out.println(user2 + "'s score: " + calculateScores(player2, player1));
    }
    
    /**
     * <p>Used to show a player's hand.</p>
     * @param player acted upon
     */
    public static void declareHand(Player player) {
        
        String handString = "";
        for (Card card : player.getHand()) {
            handString += card.getType() + ", ";
        }
        System.out.println(player.getName() + "'s hand:\n["
                + handString.substring(0, handString.length() - 2) + "]");
    }
    
    /**
     * <p>Used to show a player's field.</p>
     * @param player acted upon
     */
    public static void declareField(Player player) {
        
        String fieldString = "";
        for (Card card : player.getField()) {
            fieldString += card.getType() + ", ";
        }
        System.out.println(player.getName() + "'s played cards:\n["
                + fieldString.substring(0, fieldString.length() - 2) + "]");
    }

    /**
     * <p>Used to deal cards to a player at the beginning of a round.</p>
     * @param player acted upon
     */
    public static void deal(Player player) {
        
        for (int i = 0; i < 10; i++) {
            int randomDraw = (int) Math.floor(Math.random() * 72);
            while (drawn.contains(randomDraw)) {
                randomDraw = (int) Math.floor(Math.random() * 72);
            }
            drawn.add(randomDraw);
            player.getHand().add(deck.getCard(randomDraw));
        }
    }
    
    /**
     * <p>Iterates through a player's turn.</p>
     * @param player acted upon
     */
    public static void play(Player player) {
        
        boolean mistake = false;
        
        do {
            System.out.println(player.getName() + "'s turn!\nWhat will you play?");
            declareHand(player);
            String cardPlayed = scan.nextLine();
            mistake = true;
            for (int i = 0; i < player.getHand().size(); i++) {
                if (cardPlayed.equalsIgnoreCase(player.getHand().get(i).getType())) {
                    mistake = false;
                    player.getField().add(player.getHand().get(i));
                    player.getHand().remove(i);
                    declareField(player);
                    break;
                }
            }
            if (mistake) {
                System.out.println("You don't have that card! Try again.\n");
            }
        } while (mistake);
    }
    
    /**
     * <p>Swaps both players' hands</p>
     * @param p1 first player to be swapped
     * @param p2 second player to be swapped
     */
    public static void swap(Player p1, Player p2) {
        ArrayList<Card> temp = new ArrayList<Card>(p1.getHand());
        p1.getHand().clear();
        p1.getHand().addAll(p2.getHand());
        p2.getHand().clear();
        p2.getHand().addAll(temp);
        System.out.println("\nHands swapped!\n");
    }
    
    /**<p>Explanation of game if needed.</p> */
    public static void tutorial() {
        System.out.println("Welcome to SushiGo!\nWould you like a tutorial? (Y/N)");
        String tutorial = scan.nextLine();
        if (tutorial.equalsIgnoreCase("y")) {
            System.out.println("The goal is to get the most points after 1 round.\n"
                    + "On your turn, you play 1 card, and then swap your hand with your opponent."
                    + "\n\nPoints are as follows:\n"
                    + "EggRoll: 1 point\nSalmonRoll: 2 points\nSquidRoll: 3 points\n"
                    + "Maki: Player with the most at the end of the round gets 4 points\n"
                    + "Dumplings: 1 = 1 point, 2 = 3 points, 3 = 9 points, 4+ = 16 points\n"
                    + "Sashimi: 3+ = 10 points\nTofu: 1 = 2 points, 2 = 6 points, 3+ = 0 points\n"
                    + "\nLet's (Sushi) Go!\n");
        } else {
            System.out.println("Let's (Sushi) Go!\n");
        }
    }
    
    /**
     * <p>Calculates each player's scores based on the cards each player has
     * played (on their field).</p>
     * @param player acted upon
     * @param p2 to compare to
     * @return player's score as an integer
     */
    public static int calculateScores(Player player, Player p2) {
        int playerPoints = 0;
        for (int i = 0; i < countCards(player, "EggRoll"); i++) {
            playerPoints += 1;
        }
        for (int i = 0; i < countCards(player, "SalmonRoll"); i++) {
            playerPoints += 2;
        }
        for (int i = 0; i < countCards(player, "SquidRoll"); i++) {
            playerPoints += 3;
        }
        if (countCards(player, "Maki") > countCards(p2, "Maki")) {
            playerPoints += 4;
        }
        switch (countCards(player, "Dumplings") ) {
            case 0: playerPoints += 0;
            break;
            case 1: playerPoints += 1;
            break;
            case 2: playerPoints += 3;
            break;
            case 3: playerPoints += 9;
            break;
            default: playerPoints += 16;
        }
        if (countCards(player, "Sashimi") / 3 > 0) {
            playerPoints += 10;
        }
        switch (countCards(player, "Tofu")) {
            case 1: playerPoints += 2;
            break;
            case 2: playerPoints += 6;
            break;
            default: playerPoints += 0;
        }
        return playerPoints;
    }
    
    /**
     * <p>Used in the calculateScores method to count the number of a specific
     * type of card on the player's field</p>
     * @param player acted upon
     * @param type to be counted
     * @return count of the card type as an integer
     */
    public static int countCards(Player player, String type) {
        int count = 0;
        for (Card card : player.getField()) {
            if (card.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }
}
