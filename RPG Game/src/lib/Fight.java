package lib;

import java.io.*;

/**
 * This class enables the fight between the player and enermy
 * @author Jakub
 *
 */
public class Fight {

	/**
	 * The fight class enables the fight between player and enemy to happen and presents the user
	 * with various option they can choose from during the battle.
	 * @param player
	 * @param enemy
	 * @throws IOException
	 */
	 public Fight(Player player, Enemy enemy) throws IOException {
	        System.out.println("You encounter " + enemy + ": " + "\n");
	        System.out.println("Battle with " + enemy + " starts (" + player.getStatus() + " / "
	                + enemy.getStatus() + ")");
	        
	        //creates a new input stream for user keyboard input
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	       
	        
	        /*
	         *If the player and enemy are both alive then it presents the player with possible
	         *actions that can be chosen via keyboard input e.g. a for attack or h for heal
	         */
	        while (player.isAlive() && enemy.isAlive()) {
	            System.out.print("Attack Enemy (a) or Heal Self (h):  ");
	            String action = in.readLine();
	            
	            //If user inputs a, initiate an attack on the enemy and on the player
	            if (action.equals("a") || action.equals("A")) {
	            	enemy.defend(player);
	            	player.defend(enemy);
	            	
		            //if user input is anything apart from a or h, prompt the user that their input is invalid
					} else if (!action.equals("a") || !action.equals("A") || !action.equals("h") || !action.equals("H")) {
						System.out.println(" Invalid input");
					} 
	        
				
	            
	            //If user inputs h and there are available potions, heal and defend from enemy attack
	            if (action.equals("h") || action.equals("H") && player.havePotions()) {
	                player.heal();
	                player.defend(enemy);
	                
		                //if no potions are available, let the player know
		            } else if (!player.havePotions()) {
		            	System.out.println(" No more potions!");	
					}  
	            
	            //removed this method and implemented it into the heal/attack functions
	            /*
	             * Checks if enemy is alive and then commences the defend function which is the player
	             *	defending from the enemy's attack
	            */
	           // if (enemy.isAlive()) {
	             //   player.defend(enemy);
	            //}
	        }
	    }

	}
