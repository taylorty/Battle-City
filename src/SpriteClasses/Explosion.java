/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 22, 2016
 * Time: 4:18:53 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: Explosion
 * Description: Explosion class
 *
 * ****************************************
 */
package SpriteClasses;

import SpriteClasses.ImageUtils.Images;

/**
 * Explosion extends Animation. This is the explosion used for bullets and
 * various types of blocks.
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class Explosion extends Animation {

    public Explosion(int x, int y, Images imgateType) {
        super(x - 8, y - 8, imgateType == null ? Images.explosion : imgateType);
//        loadImage("./Battle-City/image/bullet_explosion_1.png");
//        getImageDimensions();
    }

    @Override
    public void updateAnimation() {
        if ((System.currentTimeMillis() - initialTime) > 125) {
        	updateImage(Images.explosion2);
//            loadImage("./Battle-City/image/bullet_explosion_2.png");
//            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 250)) {
        	updateImage(Images.explosion3);
//            loadImage("./Battle-City/image/bullet_explosion_3.png");
//            getImageDimensions();
        }
        if ((System.currentTimeMillis() - initialTime > 300)) {
            super.vis = false;
        }

    }

}
