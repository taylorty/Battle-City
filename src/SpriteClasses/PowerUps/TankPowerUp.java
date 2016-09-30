/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 9:13:20 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: TankPowerUp
 * Description: TankPowerUp class
 *
 * ****************************************
 */
package SpriteClasses.PowerUps;

/**
 * TankPowerUp extends the PowerUp and sets the type as 7
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class TankPowerUp extends PowerUp {
    public TankPowerUp(int x, int y) {
        super(x, y);
        loadImage("image/powerup_tank.png");
        getImageDimensions();
        setType(7);
        s = "image/powerup_tank.png";
    }

}
