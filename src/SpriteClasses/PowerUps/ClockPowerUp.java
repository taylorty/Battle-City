/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:14:40 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: ClockPowerUp
 * Description: ClockPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * ClockPowerUp extends PowerUp and sets the type as 10
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class ClockPowerUp extends PowerUp {
    public ClockPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_timer.png");
        getImageDimensions();
        setType(10);
        s = "image/powerup_timer.png";
    }

}
