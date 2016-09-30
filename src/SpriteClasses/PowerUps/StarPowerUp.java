/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:14:08 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: StarPowerUp
 * Description: StarPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * StarPowerUp extends PowerUp and sets the type as 8
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class StarPowerUp extends PowerUp {
    public StarPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_star.png");
        getImageDimensions();
        setType(8);
        s = "image/powerup_star.png";
    }

}
