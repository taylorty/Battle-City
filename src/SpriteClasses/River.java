/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 18, 2016
 * Time: 6:32:12 PM
 *
 * Project: csci205FinalProject
 * Package: SpriteClasses
 * File: River
 * Description: River class
 *
 * ****************************************
 */
package SpriteClasses;

import SpriteClasses.ImageUtils.Images;

/**
 * River is a block which represents the river blocks
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 *
 * @author Adrian Berg
 */
public class River extends Block {

    long lastImage = 0;
    boolean lastLoad = false;

    @Override
    public void updateAnimation() {
        if ((System.currentTimeMillis() - lastImage) > 500) {

            if (lastLoad) {
  //              loadImage("./Battle-City/image/water_1.png");
            	updateImage(Images.river);
                lastImage = System.currentTimeMillis();
                lastLoad = false;
            } else {
//                loadImage("./Battle-City/image/water_2.png");
            	updateImage(Images.river2);                
            	lastImage = System.currentTimeMillis();
                lastLoad = true;
            }
        }

    }

    public River(int x, int y) {
        super(x, y, Images.river);
//        loadImage("./Battle-City/image/water_1.png");
//        getImageDimensions();
        setHealth(1);
        setType(4);
    }

}
