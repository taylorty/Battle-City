/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 21, 2016
 * Time: 7:42:38 PM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: Menu
 * Description: A class for the menu of the game
 *
 * ****************************************
 */
package GameMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class for the menu of the game
 *
 * @author Tongyu
 */
public class Menu extends JPanel implements ActionListener, KeyListener {
    // Load the images from ImageUtility class
    public Image background, tank, tree;
    public GameView theView;
    private int yPos = Map.BOARD_HEIGHT;
    private int direction = -1;
    private final int stopYPos = 100;
    private static boolean menuStatus = true;
    private final ImageUtility imageInstance = ImageUtility.getInstance();

    /**
     * Constructor for the menu
     *
     * @param theView
     */
    public Menu(GameView theView) {
        this.theView = theView;
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        loadMenuImages();
        addTimer();
    }

    private void addTimer() {
        Timer timer = new Timer(10, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                yPos += direction;
                                if (yPos == stopYPos) {
                                    direction = 0;
                                } else if (yPos + background.getHeight(null) > getHeight()) {
                                    yPos = getHeight() - background.getHeight(
                                            null);
                                } else if (yPos < 0) {
                                    yPos = 0;
                                    direction *= -1;
                                }
                                repaint();
                            }
                        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void loadMenuImages() {
        background = imageInstance.getBackground();
        tank = imageInstance.getTank();
        tree = imageInstance.getTree2();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = loadFont();
        g.drawImage(background,
                    Map.BOARD_WIDTH / 2 - background.getWidth(null) / 2 - 10,
                    yPos, this);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("1 PLAYER", Map.BOARD_WIDTH / 2 - 56,
                     yPos + background.getHeight(null) + 50);
        if (yPos == stopYPos) {
            drawMenuComponents(g);
        }
    }

    private void drawMenuComponents(Graphics g) {
        g.drawImage(tree, 10, 50, this);
        g.drawImage(tree, 10, 90, this);
        g.drawImage(tank, Map.BOARD_WIDTH / 2 - 90,
                    yPos + background.getHeight(null) + 25, this);

        Font font = loadFont();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PRESS ENTER",
                     Map.BOARD_WIDTH / 2 - 80,
                     Map.BOARD_HEIGHT * 4 / 5 + 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Load the game font to the program
     *
     * @return font of the game
     */
    public static Font loadFont() {
        Font font = null;
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                                            new File("prstart.ttf"));
            font = font.deriveFont(java.awt.Font.PLAIN, 15);
            GraphicsEnvironment ge
                                = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            menuStatus = false;
            theView.getGamePanel().removeAll();
            Board panel = new Board(theView);
            theView.getGamePanel().add(panel);
            panel.requestFocusInWindow();
            theView.setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            loadBoard();
        }
    }

    /**
     * Load the board to the game panel on the JFrame of the game.
     */
    public void loadBoard() {
        menuStatus = false;
        theView.getGamePanel().removeAll();
        Board panel = new Board(theView);
        panel.revalidate();
        panel.repaint();
        theView.getGamePanel().add(panel);
        panel.requestFocusInWindow();
        theView.setVisible(true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            loadBoard();
        }
    }

    /**
     * Return if the game is showing the menu
     *
     * @return a boolean
     */
    public static boolean getMenuStatus() {
        return menuStatus;
    }
}
