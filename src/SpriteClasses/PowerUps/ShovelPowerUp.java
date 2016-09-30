/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:14:56 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: ShovelPowerUp
 * Description: ShovelPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * ShovelPowerUp extends PowerUp and sets the type at 11
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class ShovelPowerUp extends PowerUp {
    public ShovelPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_shovel.png");
        getImageDimensions();
        setType(11);
        s = "image/powerup_shovel.png";
    }

}
