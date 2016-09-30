/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 26, 2016
 * Time: 10:41:14 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: TankShield
 * Description: TankShield class
 *
 * ****************************************
 */
package SpriteClasses;

/**
 * TankShield is an animation which is created when the tank becomes
 * invulnerable either when the Tank respawns or gets a star power up
 *
 * @param Tank atank takes the player tank and uses its x and y coordinates to
 * draw the position of the shield. Also turns the players shield field off when
 * the TankShield animations is finished
 * @param int type determines how long the shield will last, type 1 is used for
 * when a powerup is used (last tens seconds) type 2 is used for a respawning
 * tank (lasts three seconds)
 * @author Adrian Berg
 */
public class TankShield extends Animation {
    long initialTime = System.currentTimeMillis();
    private Tank tank;
    private boolean flip = false;
    private int type;

    public TankShield(Tank atank, int type) {
        super(atank.x, atank.y);
        tank = atank;
        loadImage("image/shield_1.png");
        getImageDimensions();
        this.type = type;
    }

    @Override
    public void updateAnimation() {
        int shieldTime;
        if (type == 1) {
            shieldTime = 10000;
        } else {
            shieldTime = 3000;
        }
        super.x = tank.x;
        super.y = tank.y;
        long timeDifference = (System.currentTimeMillis() - initialTime);
        if (timeDifference % 10 == 0 && flip == false) {
            loadImage("image/shield_1.png");
            getImageDimensions();
            flip = true;
        } else if (timeDifference % 10 == 0 && flip == true) {
            loadImage("image/shield_2.png");
            getImageDimensions();
            flip = false;
        }
        if ((System.currentTimeMillis() - initialTime > shieldTime)) {
            tank.shield = false;
            super.vis = false;
        }
    }
}
