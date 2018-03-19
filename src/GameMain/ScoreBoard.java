/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2016
*
* Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
* Date: Apr 23, 2016
* Time: 2:15:33 AM
*
* Project: csci205FinalProject
* Package: GameMain
* File: ScoreBoard
* Description: A class for showing the totalScore
*
* ****************************************
 */
package GameMain;

import static GameMain.Menu.loadFont;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;

import GameMain.Options.OptionsEnum;
import SpriteClasses.ImageUtils;
import SpriteClasses.ImageUtils.Images;

/**
 * A class for showing the totalScore
 *
 * @author Tongyu
 */
@SuppressWarnings("serial")
public class ScoreBoard extends JPanel implements ActionListener, KeyListener {

    /**
     * Initialize instance variables for the ScoreBoard
     */
    private GameView theView;
    private int stage, totalTankNum, totalTankNum2;
    private int totalScore = 0;
    private int totalScore2 = 0;
//    private final int SHIFT = 80;
    private JButton restartButton;
//    private final ImageUtility imageInstance = ImageUtility.getInstance();
    private int[] tankScoreList = {0, 0, 0, 0};
    private int[] tankNumList = {0, 0, 0, 0};
    private int[] tankScoreList2 = {0, 0, 0, 0};
    private int[] tankNumList2 = {0, 0, 0, 0};

    /**
     * Constructor for the ScoreBoard. A restart button is added for the player
     * to restart the game
     *
     * @param theView GameView that represents the frame of the game
     */
    public ScoreBoard(GameView theView) {
        this.theView = theView;
        this.setFocusable(true);
        theView.setForeground(Color.BLACK);
        this.setLayout(null);

        restartButton = new JButton();
        restartButton.setText("Restart");
        this.add(restartButton);
        restartButton.setBounds(400, getYScaled(400), 100, 30);
        restartButton.addActionListener(this);

        loadScore();
        
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component)evt.getSource();
                ImageUtils.setNewFormSize(c.getSize().width, c.getSize().height);
                restartButton.setBounds(ImageUtils.getCurrent_form_width() - 128, getYScaled(400), 100, 30);
            }
		}); 
        
    }
    
    private int getXScaled(int x) {
    	return (int)(ImageUtils.getCurrent_scale_factor() * x) + ImageUtils.getDraw_from_x();
    }

    private int getYScaled(int y) {
    	return y;
//    	return (int)(ImageUtils.getCurrent_scale_factor() * y) + ImageUtils.getDraw_from_y();
    }

    /**
     * Draw the scoreBorad with different types of enemies on the screen.
     *
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        stage = Board.getStage();
        super.paintComponent(g);
        Font font = loadFont();
        ArrayList<Image> tankList = new ArrayList<>(
                Arrays.asList(ImageUtils.getOriginImage(Images.aiTank_basic_up),
                		ImageUtils.getOriginImage(Images.aiTank_fast_up),
                		ImageUtils.getOriginImage(Images.aiTank_power_up),
                		ImageUtils.getOriginImage(Images.aiTank_armor_up)));

        // Display High totalScore
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("STAGE   " + String.valueOf(stage), ImageUtils.getCurrent_form_width() / 2 - 57, getYScaled(60));

        g.setColor(Color.RED);
        g.drawString("1-PLAYER", getXScaled(50), getYScaled(95));

        g.setColor(Color.orange);
        g.drawString(String.valueOf(totalScore), getXScaled(50), getYScaled(130));

        for (int i = 0; i < 4; i++) {
            g.drawImage(tankList.get(i), getXScaled(216), getYScaled(160 + (i * 45)), this);
            g.drawImage(ImageUtils.getOriginImage(Images.arrow), getXScaled(186), getYScaled(168 + (i * 45)),
                        this);
        }
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(tankScoreList[i]), getXScaled(45),
            		getYScaled(180 + (i * 45)));
            g.drawString("PTS", getXScaled(95), getYScaled(180 + (i * 45)));
        }

        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(tankNumList[i]), getXScaled(170),
            		getYScaled(180 + (i * 45)));
        }

        // total underline
        g.drawLine(getXScaled(40), getYScaled(330), getXScaled(227), getYScaled(330));

        g.drawString("TOTAL", getXScaled(75), 355);
        g.drawString(String.valueOf(totalTankNum), getXScaled(170), getYScaled(355));
        g.setFont(font);
        g.setColor(Color.WHITE);
        
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) {
            g.setColor(Color.RED);
            g.drawString("2-PLAYER", ImageUtils.getCurrent_form_width() / 2 + getXScaled(50), getYScaled(95));

            g.setColor(Color.orange);
            g.drawString(String.valueOf(totalScore2), ImageUtils.getCurrent_form_width() / 2 + getXScaled(50), getYScaled(130));

            for (int i = 0; i < 4; i++) {
                g.drawImage(tankList.get(i), ImageUtils.getCurrent_form_width() / 2 + getXScaled(216), getYScaled(160 + (i * 45)), this);
                g.drawImage(ImageUtils.getOriginImage(Images.arrow), ImageUtils.getCurrent_form_width() / 2 + getXScaled(186), getYScaled(168 + (i * 45)),
                            this);
            }
            for (int i = 0; i < 4; i++) {
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(tankScoreList2[i]), ImageUtils.getCurrent_form_width() / 2 + getXScaled(45),
                		getYScaled(180 + (i * 45)));
                g.drawString("PTS", ImageUtils.getCurrent_form_width() / 2 + getXScaled(95), getYScaled(180 + (i * 45)));
            }

            for (int i = 0; i < 4; i++) {
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(tankNumList2[i]), ImageUtils.getCurrent_form_width() / 2 + getXScaled(170),
                		getYScaled(180 + (i * 45)));
            }

            // total underline
            g.drawLine(ImageUtils.getCurrent_form_width() / 2 + getXScaled(40), getYScaled(330), ImageUtils.getCurrent_form_width() / 2 + getXScaled(227), getYScaled(330));

            g.drawString("TOTAL", ImageUtils.getCurrent_form_width() / 2 + getXScaled(75), 355);
            g.drawString(String.valueOf(totalTankNum2), ImageUtils.getCurrent_form_width() / 2 + getXScaled(170), getYScaled(355));
            g.setFont(font);
            g.setColor(Color.WHITE);
        }
        
    }

    /**
     * Load the totalScore of the player from the CollisionUtility class.
     */
    public void loadScore() {
        for (int i = 0; i < 4; i++) {
            int[] enemyTankNum = CollisionUtility.getEnemyTankNum();
            tankNumList[i] = enemyTankNum[i];
        }
        for (int i = 0; i < 4; i++) {
            tankScoreList[i] = tankNumList[i] * 100 * (i + 1);
        }
        for (Integer score : tankScoreList) {
            totalScore += score;
        }
        for (Integer num : tankNumList) {
            totalTankNum += num;
        }
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) {
            for (int i = 0; i < 4; i++) {
                int[] enemyTankNum2 = CollisionUtility.getEnemyTankNum2();
                tankNumList2[i] = enemyTankNum2[i];
            }
            for (int i = 0; i < 4; i++) {
                tankScoreList2[i] = tankNumList2[i] * 100 * (i + 1);
            }
            for (Integer score : tankScoreList2) {
                totalScore2 += score;
            }
            for (Integer num : tankNumList2) {
                totalTankNum2 += num;
            }
        }
    }

    /**
     * Restart the game, load the menu and reset player's totalScore.
     */
    public void restart() {
        Board.gameOver = false;
        CollisionUtility.resetScore();
        Menu.loadMenu(theView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            restart();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	Menu.loadMenu(theView);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	Menu.loadMenu(theView);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	Menu.loadMenu(theView);
        }
    }
}
