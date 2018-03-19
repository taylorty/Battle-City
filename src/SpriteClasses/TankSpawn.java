/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 24, 2016
 * Time: 10:46:37 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: TankSpawn
 * Description: TankSpawn class
 *
 * ****************************************
 */
package SpriteClasses;

import SpriteClasses.ImageUtils.Images;

/**
 * TankSpawn is an animation used when enemy tanks are spawned
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Adrian Berg
 */
public class TankSpawn extends Animation {

    public TankSpawn(int x, int y) {
        super(x, y, Images.appear);
//        loadImage("./Battle-City/image/appear_1.png");
//        getImageDimensions();
    }

    @Override
    public void updateAnimation() {
        if ((System.currentTimeMillis() - initialTime) > 100) {
//            loadImage("./Battle-City/image/appear_2.png");
//            getImageDimensions();
        	updateImage(Images.appear2);
        }
        if ((System.currentTimeMillis() - initialTime > 200)) {
//            loadImage("./Battle-City/image/appear_3.png");
//            getImageDimensions();
        	updateImage(Images.appear3);
        }
        if ((System.currentTimeMillis() - initialTime > 300)) {
//            loadImage("./Battle-City/image/appear_4.png");
//            getImageDimensions();
        	updateImage(Images.appear4);
        }
        if ((System.currentTimeMillis() - initialTime > 400)) {
//            loadImage("./Battle-City/image/appear_1.png");
//            getImageDimensions();
        	updateImage(Images.appear);
        }
        if ((System.currentTimeMillis() - initialTime > 500)) {
//            loadImage("image/appear_2.png");
//            getImageDimensions();
        	updateImage(Images.appear2);
        }
        if ((System.currentTimeMillis() - initialTime > 600)) {
//            loadImage("./Battle-City/image/appear_3.png");
//            getImageDimensions();
        	updateImage(Images.appear3);
        }
        if ((System.currentTimeMillis() - initialTime > 700)) {
//            loadImage("./Battle-City/image/appear_4.png");
//            getImageDimensions();
        	updateImage(Images.appear4);
        }
        if ((System.currentTimeMillis() - initialTime > 800)) {
            super.vis = false;
        }
    }
}
