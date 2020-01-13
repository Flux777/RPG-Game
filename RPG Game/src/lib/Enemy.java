package lib;
import java.util.*;

public final class Enemy {
	
		private final String enemyName;
	    //private final String description;
	    private final int minDmg;
	    private final int maxDmg;
	    private int enemyHp;
		
	    private final static Random random = new Random();
	    
	    private final static Set<Integer> enemiesSeen = new HashSet<Integer>();
	    private final static int NUM_ENEMIES = 3;

	    public static Enemy newRandomInstance() {
	        if (enemiesSeen.size() == NUM_ENEMIES) {
	            enemiesSeen.clear();
	        }
	        
	        int i;
	        do {
	            i = random.nextInt(NUM_ENEMIES);
	        } while (enemiesSeen.contains(i));
	        enemiesSeen.add(i);

	        if (i == 0) {
	            return new Enemy("Gryphin", 40, 8, 12);
	        } 
	        else if (i == 1) {
	            return new Enemy("Gargoyle", 26, 4, 6);
	        }else {
	            return new Enemy("Wolf", 18, 1, 2);
	        }
	    }

	    public static Enemy newBossInstance() {
	        return new Enemy("Black Dragon", 60, 10, 20);
	    }

	    private Enemy(String enemyName, int enemyHp, int minDmg, int maxDmg)  {
	        this.enemyName = enemyName;
	        this.enemyHp = enemyHp;
	        this.minDmg = minDmg;
	        this.maxDmg = maxDmg;
	    }

	    @Override
	    public String toString() {
	        return enemyName;
	    }

	    //public String getDescription() {
	      //  return description;
	    //}

	    public String getStatus() {
	        return "Enemy HP: " + enemyHp;
	    }

	    public int attack() {
	        return random.nextInt(maxDmg - minDmg + 1) + minDmg;
	    }

	    public void defend(Player player) {
	        int attackStrength = player.attack();
	        enemyHp = (enemyHp > attackStrength) ? enemyHp - attackStrength : 0;
	        
	        System.out.printf("  %s hits %s for %d HP of damage (%s)\n", player, enemyName, attackStrength,
	                getStatus());
	        
	        if (enemyHp == 0) {
	            System.out.println("  " + player + " swiftly slices the  " + enemyName
	                    + " with his silver sword and the " + enemyName + " falls to the ground");
	        }
	    }

	    public boolean isAlive() {
	        return enemyHp > 0;
	    }
	}


