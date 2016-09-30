/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:14:27 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: BombPowerUp
 * Description: BombPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * BombPowerUp extends PowerUp
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class BombPowerUp extends PowerUp {
    public BombPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_grenade.png");
        getImageDimensions();
        setType(9);
        s = "image/powerup_grenade.png";
    }

}
