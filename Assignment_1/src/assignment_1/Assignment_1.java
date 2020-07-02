
package assignment_1;

/**
 *
 * @author dylan
 */
public class Assignment_1 {
    // global variables to hold the total wins, losses, and rolls per round
    public static int wins = 0;
    public static int losses = 0;
    public static int rolls = 0;
    
    
    public static void main(String[] args) {
        // point to be zero (this just shows the program that the roll is the first in the round)
        int point = 0;
        
        for (int i = 0; i < 100000; i++) {
            // roll the two dice and get the total
            int dice1 = (int) ((Math.random() * 5) + 1);
            int dice2 = (int) ((Math.random() * 5) + 1);
        
            int diceTotal = dice1 + dice2;
            // increment rolls to 1 
            rolls++;
            
            // if to print the info but only for the first 10 rounds
            if (i < 10) {
            System.out.println("Round " + (i + 1) + ", Roll " + rolls + " -- " + "Die1: " + dice1 + ", Die2: " + dice2 + " -- Total: " + diceTotal);
            }
            
            
            // begins checking the diceTotal to see if it is a win or a loss or neither
            if ((diceTotal == 7) || (diceTotal == 11)) {
                // if the firt roll was a 7 or 11 then increment wins and set rolls back to zero
                wins++;
                rolls = 0;
                // for the first 10 rounds print win and the wins and losses
                if (i < 10) {
                System.out.println("WIN!");
                    System.out.println("Wins: " + wins + " Losses: " + losses + "\n");
                }
            // if the first roll was a 2, 3, or 12 then increment losses and set rolls back to zero
            } else if (((diceTotal == 2) || (diceTotal == 3) || (diceTotal == 12))) {
                losses++;
                rolls = 0;
                // for the first 10 rounds print lose and the wins and losses
                if (i < 10) {
                System.out.println("LOSE!");
                    System.out.println("Wins: " + wins + " Losses: " + losses + "\n");
                }
            // if the first roll was none of the above then set the diceTotal as the point and say what the point is for the first 10 rounds
            } else {
                point = diceTotal;
                if (i < 10) {
                    System.out.println("Point is: " + point);
                }
                // boolean to continue rolling dice until the point is rolled or a 7 is rolled
                boolean keepRolling = true;
                
                
                while (keepRolling) {
                    // while loop will keep rolling and increment rolls until point or 7 is rolled 
                    dice1 = (int) ((Math.random() * 5) + 1);
                    dice2 = (int) ((Math.random() * 5) + 1);
            
                    diceTotal = dice1 + dice2;
                    rolls++;
                    // for the first 10 rounds to print the round and the rolls until the while loop stops
                    if (i < 10) {
                            System.out.println("Round " + (i + 1) + ", Roll " + rolls + " -- " + "Die1: " + dice1 + 
                                    ", Die2: " + dice2 + " -- Total: " + diceTotal);    
                    }
                        
                    // this is where the dice are tested to see if they are the point or 7 or neither
                    if (point == diceTotal) {
                        // if the point is rolled then increment wins and reset the rolls to 0. Also boolean keepRolling is set to false to end the while loop
                        wins++;
                        rolls = 0;
                        keepRolling = false;
                        if (i < 10) {
                            System.out.println("WIN!");
                            System.out.println("Wins: " + wins + " Losses: " + losses + "\n");
                        }
                    // if 7 is rolled then increment losses and set rolls to be 0. Also same as above set keepRolling to false to end the while loop
                    } else if (diceTotal == 7) {
                        losses++;
                        rolls = 0;
                        keepRolling = false;
                        if (i < 10) {
                            System.out.println("LOSE!");
                            System.out.println("Wins: " + wins + " Losses: " + losses + "\n");
                        }
                    // if neither the point or the 7 are rolled then make the loop continue rolling until one of the two are rolled
                    } else {
                        keepRolling = true;
                    } 
                // end of while
                }
            // end of else
            } 
        
       
         
            
        // end of for loop
        }
        // after the for loop prints the total wins and losses using the global variable that was incremented throughout
        System.out.println("\nWins: " + wins + " Losses: " + losses);
    // end of main
    }
    
}
