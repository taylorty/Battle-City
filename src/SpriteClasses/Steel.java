/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 18, 2016
 * Time: 5:50:00 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: Steel
 * Description: Steel class
 *
 * ****************************************
 */
package SpriteClasses;

/**
 * Steel is a Block with type 2 and health 1
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class Steel extends Block {

    public Steel(int x, int y) {
        super(x, y);
        loadImage("image/wall_steel.png");
        getImageDimensions();
        setHealth(1);
        setType(2);
    }

}
