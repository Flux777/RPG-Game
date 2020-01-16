package lib;

import java.util.Random;
/**
 * The player class contains all player details including actions that the player can do e.g.
 * heal or attack and get methods.
 * @author Jakub
 *
 */
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
	     * Constructor for the Player Class
	     * @param playerName : the name of the player
	     * @param playerHp : the current hit points of the player
	     * @param maxHp : the max hit points of the player
	     * @param minDmg : player's minimum possible damage
	     * @param maxDmg : player's maximum possible damage
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
	    //Functions
	    /**
	     * The attack function chooses a random int from the range of (maxDmg - minDmg + 1) + minDmg
	     * For example ((20 - 10 + 1) + 10), the attack will always be of at least 10 damage because 
	     * minDmg is added at the end. This also means that attack is never less that minDmg and
	     * never more than maxDmg.
	     * @return
	     */
	    public int attack() {
	        return random.nextInt(maxDmg - minDmg + 1) + minDmg;
	    }
	    
	    /**
	     * This function is used for when the player is attacked by the enemy.
	     * 
	     * @param enemy
	     */
	    public void defend(Enemy enemy) {
	        int attackStrength = enemy.attack();
	        
	        //checks if playerHp is less than attackStrength and if it is then it makes sure that playerHp -
	        //attackStrength will always be equal to 0 and will not go into negative numbers
	        playerHp = (playerHp > attackStrength) ? playerHp - attackStrength : 0;
	        
	        //prints out the damage that the player is hit for and how much hp the player has left
	        System.out.printf("  " + playerName + " is hit for %d HP of damage (%s)\n", attackStrength,
	                getStatus());
	        
	        //checks player health and lets the player know if he has been defeated after each enemy attack
	        if (playerHp == 0) {
	            System.out.println("  " + playerName + " has been defeated");
	        }
	    }
	    
	    
	    /**
	     * Healing function uses one of the player's potions to heal the player for 20 points of health
	     * meanwhile reducing the potion count by 1. If there are no potions left, let the player know
	     * that they have exhausted their supply.
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
	     * Checks if player is still alive by returning true if playerHp is above 0
	     * @return
	     */
	    public boolean isAlive() {
	        return playerHp > 0;
	    }
	    /**
	     * Gets the status of the player's Hp by printing a string followed by the playerHp
	     * @return
	     */
	    public String getStatus() {
	        return "Player HP: " + playerHp;
	    }
	    /**
	     * The toString method for the playerName which returns the name in a string
	     */
	    @Override
	    public String toString() {
	        return playerName;
	    }
	    /**
	     * Returns the player's description as a string
	     * @return
	     */
	    public String getDescription() {
	        return description;
	    }
	    /**
	     * Function for checking if the player has any more potions left, returns true if
	     * potions > 0 and false if otherwise.
	     * @return
	     */
	    public boolean havePotions() {
	    	return potions > 0;
	    }
	    
	    /**
	     * Initialises a new instance that also initialises the player's details with the following
	     * inputs
	     * @return
	     */
	    public static Player newInstance() {
	        return new Player("Mighty Warrior","with a thirst for adventure", 
	        		40, 6, 10, 20);
	    }
	}
