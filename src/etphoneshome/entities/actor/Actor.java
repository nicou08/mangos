package etphoneshome.entities.actor;

import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.SpriteURL;
import etphoneshome.objects.Hitbox;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

/**
 * This class is used as the parent class for the {@code Character} and the {@code Enemy}. Using the getLocation method
 * returns a location object. Using setLocation allows you to set the actor's location to a specified
 * location. Using getIsDead checks if this actor is currently dead. Using setIsDead allows you to
 * set whether this actor is currently dead. Using getHealth returns the current health of the {@code actor}.
 * Using setHealth allows you to set the health of the {@code actor} and updates isDead status accordingly.
 * Using takeSinglePointOfDamage applies a single point of damage to the {@code actor} health and updates isDead
 * status accordingly. Using getEntitySprite returns the image object of the {@code actor}. Using setEntitySprite allows
 * you to change the image object of the {@code actor} using a URL/file path address.
 */
public abstract class Actor {
    private JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached
    /**
     * Status of whether this actor is dead
     */
    private boolean isDead = false;    //Should be set to true if the actor dies

    /**
     * Location associated with the {@code actor}
     */
    private Location location = new Location(0, 0);

    /**
     * Amount of lives/health associated with the {@code actor}
     */
    private int health = 1;     //can be used for both characters and enemies. A default value of 1 is given

    /**
     * A placeholder image associated with a generic {@code actor}
     */
    private Image leftEntitySprite = new Image(SpriteURL.PLACEHOLDER_SPRITE.getPath());

    /**
     * A placeholder image associated with a generic {@code actor}
     */
    private Image rightEntitySprite = new Image(SpriteURL.PLACEHOLDER_SPRITE.getPath());


    /**
     * A variable that tracks if the actor is facing right or left
     */
    private boolean facingRight = true;

    /**
     * The velocity object associated with the {@code actor} with the default values of 0
     */
    private Velocity velocity = new Velocity();

    /**
     * The hitbox object associated with the {@code Hitbox}
     */
    private Hitbox hitbox = new Hitbox(null, 0, 0);

    /**
     * empty default constructor
     */
    public Actor() {
    }

    /**
     * constructor with intial location of {@code actor}
     *
     * @param location initial location of {@code actor}
     */
    public Actor(Location location) {
        this.setLocation(location);
    }

    /**
     * Returns the location object associated with the {@code actor}
     *
     * @return The location object associated with the {@code actor}
     */
    public Location getLocation() {
        return new Location(this.location);
    }

    /**
     * Sets the location object associated with the {@code actor}
     *
     * @param newLocation The new {@code location} object
     */
    public void setLocation(Location newLocation) {
        this.location = new Location(newLocation);
        this.hitbox.setLocation(this.location);
    }

    /**
     * Returns the status of whether the actor is dead
     *
     * @return The current {@code isDead} status of the actor
     */
    public boolean getIsDead() {     //true means the actor is dead
        return this.isDead;
    }

    /**
     * Sets the status of whether the actor is dead to a new status
     *
     * @param newStatus The new status of whether the {@code actor} is dead
     */
    public void setIsDead(boolean newStatus) {       //true means the actor is dead
        this.isDead = newStatus;
    }

    /**
     * Returns the current health amount of the {@code actor}
     *
     * @return The current {@code health} of the {@code actor}
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the health of the {@code actor} to a new amount which must be non negative.
     *
     * @param newHealth The new {@code health} amount for the {@code actor}
     */
    public void setHealth(int newHealth)    //This could be useful for instantly killing an enemy, setting initial-
    {                                       //-health or if we implement healing in the future
        if (newHealth >= 0)      //we can only have non negative health
        {
            this.health = newHealth;
        } else {
            System.out.println("Health cannot be negative! Heatlh is unchanged");
        }

        if (this.getHealth() <= 0) {
            this.setIsDead(true);
        } else {
            this.setIsDead(false);
        }
    }

    /**
     * Applies 1 point of damage to the {@code actor} and checks if the actor is dead
     */
    public void takeSinglePointOfDamage() {
        this.health = this.health - 1;

        if (this.health <= 0)    //indicates the actor is dead
        {
            setIsDead(true);
        } else {
            setIsDead(false);
        }
    }


    /**
     * Returns the (right-facing) image/sprite object associated with the {@code actor}
     *
     * @return The (right-facing) image/sprite object associated with the {@code actor}
     */
    public Image getRightEntitySprite() {
        return this.rightEntitySprite;
    }


    /**
     * Returns the (left-facing) image/sprite object associated with the {@code actor}
     *
     * @return The (left-facing) image/sprite object associated with the {@code actor}
     */
    public Image getLeftEntitySprite() {
        return this.leftEntitySprite;
    }

    /**
     * Assigns a new (right-facing) image/sprite to the {@code actor}
     *
     * @param newSpriteURL The URL/file address of the new imagee/sprite
     */
    public void setRightEntitySprite(String newSpriteURL) {
        this.rightEntitySprite = new Image(newSpriteURL);
        this.hitbox = new Hitbox(this.location, (int) this.rightEntitySprite.getHeight(), (int) this.rightEntitySprite.getWidth());
    }

    /**
     * Assigns a new (left-facing) image/sprite to the {@code actor}
     *
     * @param newSpriteURL The URL/file address of the new imagee/sprite
     */
    public void setLeftEntitySprite(String newSpriteURL) {
        this.leftEntitySprite = new Image(newSpriteURL);
        this.hitbox = new Hitbox(this.location, (int) this.leftEntitySprite.getHeight(), (int) this.leftEntitySprite.getWidth());
    }

    /**
     * Returns velocity of the {@code actor}
     *
     * @return The velocity of the {@code actor}
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Returns true if the actor is facing right, and false otherwise
     *
     * @return {@code facingRight}
     */
    public boolean isFacingRight() {
        return this.facingRight;
    }


    /**
     * Udpates the {@code facingRight} value
     *
     * @param facingRight new {@code facingRight} value
     */
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    /**
     * Returns the hitbox object associated with this {@code actor}
     *
     * @return {@code Hitbox}
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.hitbox);
    }

    //main tests the class methods
    public static void main(String[] args) {
        Actor a = new ET(new Location(0, 0));
        //test the getter and setter for isDead

        if (a.getIsDead()) //should be false originally
        {
            System.out.println("This should not have been printed. actor should be alive (but is dead here)");
        } else {
            System.out.println("actor is alive. This is the correct outcome");
        }

        a.setIsDead(true);

        if (a.getIsDead()) //should be true
        {
            System.out.println("actor is dead. This is the correct outcome.");
        } else {
            System.out.println("actor is alive. This is not the correct outcome");
        }

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(153, 238);

        testLoc = a.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        a.setLocation(placeIntoTestLoc);    //sets location in a to the new values

        testLoc = a.getLocation();  //gets the location from a which is now changed

        System.out.println("testLoc x coordinate (should be 153): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 238): " + testLoc.getYcord());

        //tests for health, getHealth, setHealth and takeSinglePointOfDamage
        System.out.println("Testing health. Should be 1: " + a.getHealth());
        a.setHealth(8);
        System.out.println("Testing health. Should be 8: " + a.getHealth());
        a.takeSinglePointOfDamage();
        a.takeSinglePointOfDamage();
        a.takeSinglePointOfDamage();
        System.out.println("Testing health. Should be 5: " + a.getHealth());
        a.setHealth(-1);
        System.out.println("Testing health. Should be 5:" + a.getHealth());
        a.setHealth(1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.setHealth(0);
        System.out.println("Testing health. Should be 0:" + a.getHealth());
        System.out.println("Should be true: " + a.getIsDead());
        a.setHealth(1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.setHealth(-1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.takeSinglePointOfDamage();
        System.out.println("Testing health. Should be 0:" + a.getHealth());
        System.out.println("Should be true: " + a.getIsDead());

        //entitySprite methods can't be tested until we setup the display window

        System.out.println("Testing velocity. Should be 0.0: " + a.velocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be 0.0: " + a.velocity.getVerticalVelocity());

        a.velocity.setHorizontalVelocity(200);
        a.velocity.setVerticalVelocity(-125);

        System.out.println("Testing velocity. Should be 200.0: " + a.velocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be -125.0: " + a.velocity.getVerticalVelocity());

        a.velocity.changeHorizontalVelocity(-250);
        a.velocity.changeVerticalVelocity(126);

        System.out.println("Testing velocity. Should be -50.0: " + a.velocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be 1.0: " + a.velocity.getVerticalVelocity());

    }
}