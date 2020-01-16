package lib;
import java.io.IOException;
import java.util.*;
/**
 * The Room class creates all rooms that the player will go through along with a separate description
 * for each room including the boss room
 * @author Jakub
 *
 */
public final class Room {
	/**
	 * Fields
	 */
    private final String description;
    private final Enemy enemy;
    private final Boolean isBossRoom;
    
    private final static Random random = new Random();
    private final static Set<Integer> roomsSeen = new HashSet<Integer>();
    private final static int NUM_ROOMS = 7;

    /**
     * Constructors
     * @param description
     * @param enemy
     * @param isBossRoom
     */
    private Room(String description, Enemy enemy, Boolean isBossRoom) {
        this.description = description;
        this.enemy = enemy;
        this.isBossRoom = isBossRoom;
    }
    //Functions
    /**
     * This function creates a new variable roomsSeen that counts the number of rooms the player has 
     * visited, this number cannot go above the value of NUM_ROOMS. A random number is picked for the
     * value 'i' which is then passed to the if statements which decide which description will be 
     * assigned to that regular room.  
     * @return
     */
    public static Room newRegularInstance() {
    	//if roomsSeen reaches the last possibility (7), roomsSeen is cleared
        if (roomsSeen.size() == NUM_ROOMS) {
            roomsSeen.clear();
        }
        	//sets the int 'i' to a random int that exists in NUM_ROOMS while roomsSeen
        	//contains int 'i', add 'i' to roomsSeen.
        	int i;
	        do { i = random.nextInt(NUM_ROOMS);
	        } while (roomsSeen.contains(i));
	        	roomsSeen.add(i);
	        	
	    //all different room description possibilities chosen depending on the value of i
        String roomDescription = null;
        if (i == 0) {
            roomDescription = "an eerie, dark cave teeming with foul beasts";
        } else if (i == 1) {
            roomDescription = "an endless mountain range where eagles soar looking for prey";
        } else if (i == 2) {
            roomDescription = "a stale swamp with a foul and toxic odour";
        } else if (i == 3) {
            roomDescription = "a bubbling volcano with a river of magma flowing by your feet";
        } else if (i == 4) {
            roomDescription = "a thick forest where it's silent yet strange voices call out from the trees high above";
        } else if (i == 5) {
            roomDescription =  "an old abandoned sailing ship, littered with remains of the unlucky crew";
        } else if (i == 6) {
            roomDescription = "a ruined tavern with a campfire roaring in the corner";
        } else {
        }
        return new Room(roomDescription, Enemy.newRandomInstance(), false);
    }
    
    /**
     * Function for creating new boss room, assigning it as a Room and giving it a description
     * @return
     */
    public static Room newBossInstance() {
        return new Room("a huge cavern thick with the smell of sulfur,  "
        		+ "as you enter the volcano embedded hideout you can sense another presence", Enemy.newBossInstance(),
                true);
    }
    /**
     * Function for checking if a room is the boss room and therefore returning true
     * @return
     */
    public boolean isBossRoom() {
        return isBossRoom;
    }
    /**
     * Function for checking if the enemy is not alive and therefore returning true
     * @return
     */
    public boolean isComplete() {
        return !enemy.isAlive();
    }
    
    /**
     * The toString method for returning room description
     */
    @Override
    public String toString() {
        return description;
    }
    
    /**
     * Function for the player entering rooms. As the player enters a new room they are given
     * a introduction along with description of the room. If enemy in that room is alive, this
     * function initiates a fight between the player and the enemy.
     * @param player
     * @throws IOException
     */
    public void enter(Player player) throws IOException {
        System.out.println("You are in " + description);
        if (enemy.isAlive()) {
            new Fight(player, enemy);
        }
    }

}
