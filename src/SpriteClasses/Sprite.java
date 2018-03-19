/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 16, 2016
 * Time: 10:49:27 PM
 *
 * Project: csci205FinalProject
 * Package: scratch
 * File: Sprite
 * Description: Sprite class
 *
 * ****************************************
 */
package SpriteClasses;

/**
 * This sprite classes is TAKEN DIRECTLY FROM ONLINE Here is the link,
 * http://zetcode.com/tutorials/javagamestutorial/collision/ Almost all objects
 * in our game inherit Sprite
 *
 * @author Adrian Berg
 */
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
    public int x;
    public int y;
    public int width = 16;
    public int height = 16;
    public boolean vis;
//    private Image image;
    protected ImageUtils.Images imageType;
    
    public Sprite(int x, int y, ImageUtils.Images imageType) {
        this.x = x;
        this.y = y;
        vis = true;
        this.imageType = imageType;
    }

//    protected void getImageDimensions() {
//    	if (getImage() == null) return;
//        width = getImage().getWidth(null);
//        height = getImage().getHeight(null);
//    }

//    protected void loadImage(String imageName) {
//        ImageIcon i = new ImageIcon(imageName);
//        image = i.getImage();
//    }

    public Image getImage() {
//        return image;
    	return ImageUtils.getImage(this.imageType);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void updateImage(ImageUtils.Images imageType) {
    	this.imageType = imageType;
    }
}
