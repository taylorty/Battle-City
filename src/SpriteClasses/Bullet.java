/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 17, 2016
 * Time: 12:55:16 PM
 *
 * Project: csci205FinalProject
 * Package: scratch
 * File: Bullet
 * Description: Bullet class
 *
 * ****************************************
 */
package SpriteClasses;

import SpriteClasses.ImageUtils.Images;

/**
 * Bullet is a Sprite. Direction refers to which way the bullet is facing North
 * East South and West refer to 0/1/2/3 respectively Bullets can move with a pre
 * defined speed. Additionally, bullets can be upgraded to destroy steel bricks.
 * Bullets can be spawned either by player tank or enemy tank, this boolean is
 * helpful to stop tanks from killing themselves on the bullets that they spawn
 * Furthermore for future
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @param int direction represents the direction the bullet is facing
 * North/East/South/West correspond to 0/1/2/3 respectively
 * @param boolean Enemy represents whether an enemy tank (= true) or player tank
 * (=false) created a bullet
 *
 * @author Adrian Berg
 */
public class Bullet extends Sprite {
    private final int BULLET_SPEED = 3;
//    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
//    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
    private int direction;
    private boolean upgrade = false;
    public boolean isEnemy;
    public boolean isFirst = true;

    public Bullet(int x, int y, int direction, boolean Enemy, boolean isFirst) {
        super(x, y, null);
        this.width = 8;
        this.height = 8;
        this.direction = direction;
        if (direction == 0) {
//            loadImage("./Battle-City/image/bullet_up.png");
        	updateImage(Images.bullet_up);
        }
        if (direction == 1) {
//            loadImage("./Battle-City/image/bullet_right.png");
        	updateImage(Images.bullet_right);
        }
        if (direction == 2) {
//            loadImage("./Battle-City/image/bullet_down.png");
        	updateImage(Images.bullet_down);
        }
        if (direction == 3) {
//            loadImage("./Battle-City/image/bullet_left.png");
        	updateImage(Images.bullet_left);
        }
        this.isEnemy = Enemy;
        this.isFirst = isFirst;
//        getImageDimensions();
    }

    public void move() {
        if (direction == 0) {
            y -= BULLET_SPEED;
        } else if (direction == 1) {
            x += BULLET_SPEED;
        } else if (direction == 2) {
            y += BULLET_SPEED;
        } else if (direction == 3) {
            x -= BULLET_SPEED;
        }
        if (x > ImageUtils.getDefaultBlockSize() * 33 + 100 + width) {
            vis = false;
        }
        if (x < -width - 100) {
            vis = false;
        }
        if (y > ImageUtils.getDefaultBlockSize() * 28 + height + 100) {
            vis = false;
        }
        if (y < -height - 100) {
            vis = false;
        }
    }

    public void upgrade() {
        this.upgrade = true;
    }

    public boolean getUpgrade() {
        return this.upgrade;
    }

	public boolean isFirst() {
		return isFirst;
	}
    

}
