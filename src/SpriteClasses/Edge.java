/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 22, 2016
 * Time: 3:15:47 AM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: Edge
 * Description: Edge class
 *
 * ****************************************
 */
package SpriteClasses;

import SpriteClasses.ImageUtils.Images;

/**
 * Edge class
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @author Tongyu
 */
public class Edge extends Block {
    public Edge(int x, int y) {
        super(x, y, Images.edge);
//        loadImage("./Battle-City/image/edge.png");
//        getImageDimensions();
        setType(6);
        setHealth(1);
    }
}
