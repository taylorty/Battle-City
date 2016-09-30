/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 16, 2016
 * Time: 10:59:46 PM
 *
 * Project: csci205FinalProject
 * Package: scratch
 * File: Block
 * Description: Block class
 *
 * ****************************************
 */
package SpriteClasses;

/**
 * Block is extended from the Sprite class. It adds health and type to the
 * Sprite allowing for different types of blocks and ways to manipulate block
 * health
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 *
 * @author Adrian Berg
 */
public class Block extends Sprite {
    public int health = 1;
    private int type;

    public Block(int x, int y) {
        super(x, y);
    }

    public void lowerHealth() {
        health -= 1;
    }

    public int currentHealth() {
        return health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void updateAnimation() {

    }

}
