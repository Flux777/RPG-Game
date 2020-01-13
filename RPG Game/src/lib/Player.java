package lib;

import java.util.Random;

public final class Player{
	
	/**
	 * Fields
	 */
		public final String playerName;
		public final String description;
		private int playerHp;
		private int potions;
	    private final int maxHp;
	    private final int minDmg;
	    private final int maxDmg;
	    private final Random random = new Random();
	    
	    /**
	     * Constructors
	     * @param playerName
	     * @param playerHp
	     * @param maxHp
	     * @param minDmg
	     * @param maxDmg
	     */
	    private Player(String playerName, String description, int maxHp, int potions,  int minDmg, int maxDmg )  {
	        this.playerName = playerName;
	        this.description = description;
	        this.potions = potions;
	        this.minDmg = minDmg;
	        this.maxDmg = maxDmg;
	        this.maxHp = maxHp;
	        this.playerHp = maxHp;   
	    }
	    
	    public int attack() {
	        return random.nextInt(maxDmg - minDmg + 1) + minDmg;
	    }
	    
	    
	    public void defend(Enemy enemy) {
	        int attackStrength = enemy.attack();
	        playerHp = (playerHp > attackStrength) ? playerHp - attackStrength : 0;
	        System.out.printf("  " + playerName + " is hit for %d HP of damage (%s)\n", attackStrength,
	                getStatus());
	        if (playerHp == 0) {
	            System.out.println("  " + playerName + " has been defeated");
	        }
	    }
	    
	    /**
	     * Healing function
	     */
	    public void heal() {
	        if (potions > 0) {
	        	potions = potions - 1;
	            playerHp = Math.min(maxHp, playerHp + 20);
	            System.out.printf("  %s drinks healing potion (%s, %d potions left)\n", playerName,
	                    getStatus(), potions);
	  
	        } else {
	            System.out.println("  You've exhausted your potion supply!");
	        }
	    }
	    
	    /**
	     * Checks if player is still alive
	     * @return
	     */
	    public boolean isAlive() {
	        return playerHp > 0;
	    }

	    public String getStatus() {
	        return "Player HP: " + playerHp;
	    }

	    @Override
	    public String toString() {
	        return playerName;
	    }

	    public String getDescription() {
	        return description;
	    }
	    
	    public boolean havePotions() {
	    	return potions > 0;
	    }

	    public static Player newInstance() {
	        return new Player("Mighty Warrior","with a thirst for adventure", 
	        		40, 6, 10, 20);
	    }
	}
