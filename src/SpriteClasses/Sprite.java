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
import javax.swing.ImageIcon;

public class Sprite {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean vis;
    public Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon i = new ImageIcon(imageName);
        image = i.getImage();
    }

    public Image getImage() {
        return image;
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
}
