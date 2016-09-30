/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 26, 2016
 * Time: 10:20:36 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: Animation
 * Description: Animation class
 *
 * ****************************************
 */
package SpriteClasses;

/**
 * Animation is a child class of Sprite, it adds the functionality of
 * updateAnimation() which is a method used to change the loaded image. The
 * initialTime is used to update the image
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 *
 * @author Adrian Berg
 */
public class Animation extends Sprite {
    long initialTime = System.currentTimeMillis();

    public Animation(int x, int y) {
        super(x, y);
    }

    public void updateAnimation() {

    }

}
