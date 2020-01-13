package lib;
import java.io.IOException;
import java.util.*;

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
    /**
     * Function for creating regular instances. 
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
            roomDescription = "a fetid, dark room teeming with foul beasts";
        } else if (i == 1) {
            roomDescription = "an endless mountain range where eagles soar looking for prey";
        } else if (i == 2) {
            roomDescription = "a murky swamp with a foul smelling odour";
        } else if (i == 3) {
            roomDescription = "a volcano with rivers of lava at all sides";
        } else if (i == 4) {
            roomDescription =
                    "a thick forest where strange voices call out from the trees high above";
        } else if (i == 5) {
            roomDescription =
                    "an old abandoned sailing ship, littered with the remains of some unlucky sailors";
        } else if (i == 6) {
            roomDescription = "a cafe filled with hipster baristas who refuse to use encapsulation";
        } else {
        }
        return new Room(roomDescription, Enemy.newRandomInstance(), false);
    }
    
    /**
     * Function for creating new boss instance
     * @return
     */
    public static Room newBossInstance() {
        return new Room("a huge cavern thick with the smell of sulfur", Enemy.newBossInstance(),
                true);
    }
    /**
     * Function for checking if a room is the boss room
     * @return
     */
    public boolean isBossRoom() {
        return isBossRoom;
    }
    /**
     * Function for checking if the enemy in that instance is alive or not
     * therefore checking if that instance is complete
     * @return
     */
    public boolean isComplete() {
        return !enemy.isAlive();
    }

    @Override
    public String toString() {
        return description;
    }
    
    /**
     * Function for the player entering rooms
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
