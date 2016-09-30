/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 18, 2016
 * Time: 10:32:51 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: Base
 * Description: Base class
 *
 * ****************************************
 */
/**
 * The Base class is a block which represents the player's base. It has an
 * updateAnimation method inorder to show the base when it is destroyed.
 *
 * @Author Adrian Berg
 */
package SpriteClasses;

public class Base extends Block {
    public boolean gameOver = false;

    public Base(int x, int y) {
        super(x, y);
        loadImage("image/base.png");
        getImageDimensions();
        setHealth(1);
        setType(3);

    }

    public void updateAnimation() {
        if (gameOver == true) {
            loadImage("image/base_destroyed.png");
            getImageDimensions();

        }
    }

}
