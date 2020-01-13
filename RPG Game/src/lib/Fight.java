package lib;

import java.io.*;

public class Fight {

	 public Fight(Player player, Enemy enemy) throws IOException {
	        System.out.println("You encounter " + enemy + ": " + "\n");
	        System.out.println("Battle with " + enemy + " starts (" + player.getStatus() + " / "
	                + enemy.getStatus() + ")");
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        
	        while (player.isAlive() && enemy.isAlive()) {
	            System.out.print("Attack (a) or Heal (h)?: ");
	            String action = in.readLine();
	            
	            if (action.equals("h") && player.havePotions()) {
	                player.heal();
	            } else {
	            	System.out.println("  No more potions! Attacking enemy instead!");
	                enemy.defend(player);
	            }
	            
	            if (enemy.isAlive()) {
	                player.defend(enemy);
	            }
	        }
	    }

	}
