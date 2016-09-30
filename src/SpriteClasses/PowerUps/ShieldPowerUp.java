/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:15:09 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: ShieldPowerUp
 * Description: ShieldPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * ShieldPowerUp extends PowerUp and sets the type at 12
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class ShieldPowerUp extends PowerUp {
    public ShieldPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_helmet.png");
        getImageDimensions();
        setType(12);
        s = "image/powerup_helmet.png";
    }

}
