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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

import SpriteClasses.ImageUtils;
import SpriteClasses.ImageUtils.Images;

/**
 * A class for the menu of the game
 *
 * @author Tongyu
 */
@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener, KeyListener {
    // Load the images from ImageUtility class
    public Image background, tank, tree;
    public GameView theView;
    private int yPos = ImageUtils.getCurrent_block_size() * 28;
    private int direction = -1;
    private final int stopYPos = 100;
    private static boolean menuStatus = true;
    private int menuSelected = 0;
//    private final ImageUtility imageInstance = ImageUtility.getInstance();

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
        background = ImageUtils.getOriginImage(Images.background); //imageInstance.getBackground();
        tank = ImageUtils.getOriginImage(Images.playerTank_right); //imageInstance.getTank();
        tree = ImageUtils.getOriginImage(Images.tree2); //imageInstance.getTree2();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = loadFont();
        g.drawImage(background,
        		(ImageUtils.getCurrent_board_width() / 2 - background.getWidth(null) / 2 - 10) + ImageUtils.getDraw_from_x(),
                    yPos + ImageUtils.getDraw_from_y(), this);
        g.setFont(font);
        g.setColor(Color.WHITE);
//        g.drawString("1 PLAYER", (ImageUtils.getCurrent_board_width() / 2 - 56) + ImageUtils.getDraw_from_x(),
//                     yPos + background.getHeight(null) + 50 + ImageUtils.getDraw_from_y());
        g.drawString("START GAME", (ImageUtils.getCurrent_board_width() / 2 - 56) + ImageUtils.getDraw_from_x(),
                yPos + background.getHeight(null) + 45 + ImageUtils.getDraw_from_y());
        g.drawString("OPTIONS", (ImageUtils.getCurrent_board_width() / 2 - 56) + ImageUtils.getDraw_from_x(),
                yPos + background.getHeight(null) + 100 + ImageUtils.getDraw_from_y());
        if (yPos == stopYPos) {
            drawMenuComponents(g);
        }
    }

    private void drawMenuComponents(Graphics g) {
        g.drawImage(tree, 10, 50, this);
        g.drawImage(tree, 10, 90, this);
        g.drawImage(tank, (ImageUtils.getCurrent_board_width() / 2 - 90) + ImageUtils.getDraw_from_x(),
                    yPos + background.getHeight(null) + (menuSelected * 55) + 25 + ImageUtils.getDraw_from_y(), this);

        Font font = loadFont();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PRESS ENTER",
        		(ImageUtils.getCurrent_board_width() / 2 - 80) + ImageUtils.getDraw_from_x(),
        		ImageUtils.getCurrent_board_height() * 4 / 5 + 25 + ImageUtils.getDraw_from_y());
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
//        	File fontFile =  new File("prstart.ttf");
			URL fontUrl = ImageUtils.class.getClassLoader().getResource("font/prstart.ttf");
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,  new File(fontUrl.toURI()));
//            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
//                                            new File("prstart.ttf"));
            font = font.deriveFont(java.awt.Font.PLAIN, 15);
            GraphicsEnvironment ge
                                = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (FontFormatException | IOException | URISyntaxException ex) {
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
            if (menuSelected == 0) loadBoard();
            else loadOptions();
        }  else if (e.getKeyCode() == e.VK_UP) {
        	menuSelected--;
        	if (menuSelected < 0) menuSelected = 1;
        } else if (e.getKeyCode() == e.VK_DOWN) {
        	menuSelected++;
        	if (menuSelected > 1) menuSelected = 0;
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
    
    private void loadOptions() {
        menuStatus = false;
        theView.getGamePanel().removeAll();
        OptionBoard panel = new OptionBoard(theView);
        panel.revalidate();
        panel.repaint();
        theView.getGamePanel().add(panel);
        panel.requestFocusInWindow();
        theView.setVisible(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            if (menuSelected == 0) loadBoard();
            else loadOptions();
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
    
    /**
     * Load the menu to the game panel if the player chooses to restart the
     * game.
     */
    public static void loadMenu(GameView gameView) {
    	gameView.getGamePanel().removeAll();
        Menu menu = new Menu(gameView);
        menu.revalidate();
        menu.repaint();
        gameView.getGamePanel().add(menu);
        menu.requestFocusInWindow();
        gameView.setVisible(true);
    } 

    
}
