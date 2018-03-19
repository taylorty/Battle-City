/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 16, 2016
 * Time: 10:21:36 PM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: Board
 * Description: Board class of the game
 *
 * ****************************************
 */
package GameMain;

import static GameMain.CollisionUtility.loadCollisionUtility;
import static GameMain.CollisionUtility.resetTankPosition;
import static GameMain.Menu.loadFont;
import SpriteClasses.Animation;
import SpriteClasses.Base;
import SpriteClasses.Block;
import SpriteClasses.Brick;
import SpriteClasses.Bullet;
import SpriteClasses.Edge;
import SpriteClasses.ImageUtils;
import SpriteClasses.ImageUtils.Images;
import SpriteClasses.PowerUps.PowerUp;
import SpriteClasses.River;
import SpriteClasses.Steel;
import SpriteClasses.Tank;
import SpriteClasses.Tank2;
import SpriteClasses.TankAI;
import SpriteClasses.Tree;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameMain.Options.OptionsEnum;

/**
 * Board class of the game
 *
 * @author Adrian Berg
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
    // Instance varaible for the timer of the tank
    private Timer timer;
    private Tank tank;
    private Tank2 tank2;

    private ArrayList<TankAI> enemy;
    private ArrayList<Block> blocks;
    private ArrayList<Animation> animations = new ArrayList<>();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
//    private final ImageUtility imageInstance = ImageUtility.getInstance();

//    private final int INIT_PLAYER_X = 10 * 16;
//    private final int INIT_PLAYER_Y = (Map.level0.length - 3) * 16;
//    private final int B_WIDTH = Map.BOARD_WIDTH;
//    private final int B_HEIGHT = Map.BOARD_HEIGHT;
    private final int DELAY = 15;
    private final int initX = 29;
    private boolean pause = false;
    public static boolean gameOver = false;
    private int yPos = ImageUtils.getCurrent_block_size() * 28; // Map.BOARD_HEIGHT;
    private int direction = -1;
//    private final int stopYPos = 250;
    private GameView theView;
    private static int stage = 1;
    private int numAI;
    private static final int goal = 10;
    public static int numEnemies = goal;
    private long lastSpawnTime;

    /**
     * Constructor for the Board class
     *
     * @param theView GameView that represents the frame of the game
     */
    public Board(GameView theView) {
        this.theView = theView;
        timer = new Timer(DELAY, this);
        timer.start();
        try {
			initBoard();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }

    /**
     * Initialize the board.
     * @throws URISyntaxException 
     */
    private void initBoard() throws URISyntaxException {
        stage = Options.getInstance().getOption(OptionsEnum.start_level);
        numEnemies = goal;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setPreferredSize(new Dimension(33 * ImageUtils.getDefaultBlockSize(), 28 * ImageUtils.getDefaultBlockSize()));
        numAI = 0;
//        tank = new Tank(INIT_PLAYER_X, INIT_PLAYER_Y, 4);
        tank = new Tank(10 * ImageUtils.getDefaultBlockSize(), (Map.level0.length - 3) * ImageUtils.getDefaultBlockSize(), 4);
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) {
        	tank2 = new Tank2(18 * ImageUtils.getDefaultBlockSize(), (Map.level0.length - 3) * ImageUtils.getDefaultBlockSize(), 4);
        }

        initBlocks();
        CollisionUtility.loadCollisionUtility(blocks, animations);
        BoardUtility.loadBoardUtility(enemy, blocks, animations, powerUps, tank, tank2);
        BoardUtility.setTank2(tank2);
    }

    /**
     * Initialize blocks according to the map.
     * @throws URISyntaxException 
     */
    public void initBlocks() throws URISyntaxException {

   	 	this.blocks = new ArrayList<>();
   	 	this.enemy = new ArrayList<>();
   	 	this.tank.setBullets(new ArrayList<>());
   	 	if (Options.getInstance().getOption(OptionsEnum.mode) == 2) this.tank2.setBullets(new ArrayList<>());
        int[][] map = Map.getMap(stage);
        SoundUtility.startStage();
        int type;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                type = map[x][y];
                BlockType bType = BlockType.getTypeFromInt(type);
                switch (bType) {
                    case BRICK:
                        blocks.add(new Brick(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    case STEEL:
                        blocks.add(new Steel(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    case BASE:
                        blocks.add(new Base(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    case RIVER:
                        blocks.add(new River(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    case TREE:
                        blocks.add(new Tree(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    case EDGE:
                        blocks.add(new Edge(y * ImageUtils.getDefaultBlockSize(), x * ImageUtils.getDefaultBlockSize()));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        drawEdge(g);
        endGame(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Check if the player's health is lower than 0. If lower than 0, then end
     * the game
     */
    private void checkGameOver() {
    	if (Options.getInstance().getOption(OptionsEnum.mode) == 2) {
            if (tank.getHealth() < 0 && tank2.getHealth() < 0) {
                setEndGame();
            }
    	} else {
            if (tank.getHealth() < 0) {
                setEndGame();
            }
    	}
    }
    
    private int getXScaled(int x) {
    	return (int)(ImageUtils.getCurrent_scale_factor() * x) + ImageUtils.getDraw_from_x();
    }

    private int getYScaled(int y) {
    	return (int)(ImageUtils.getCurrent_scale_factor() * y) + ImageUtils.getDraw_from_y();
    }

    /**
     * Draw objects on the board.
     */
    private void drawObjects(Graphics g) {
        for (TankAI tankAI : enemy) {
            if (tankAI.isVisible()) {
                g.drawImage(tankAI.getImage(), getXScaled(tankAI.getX()), getYScaled(tankAI.getY()),  this);
            }
        }
        if (tank.isVisible() && tank.getHealth() >= 0) {
        	//Not understud, why. But tank is not exactly two time bigger than block size
            g.drawImage(tank.getImage(), getXScaled(tank.getX()) + (int)(ImageUtils.getCurrent_scale_factor() * 2),
            			getYScaled(tank.getY()) + (int)(ImageUtils.getCurrent_scale_factor() * 2), this);
        }
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2 && tank2.isVisible() && tank2.getHealth() >= 0) {
            g.drawImage(tank2.getImage(), getXScaled(tank2.getX()) + (int)(ImageUtils.getCurrent_scale_factor() * 2),
            			getYScaled(tank2.getY()) + (int)(ImageUtils.getCurrent_scale_factor() * 2), this);
        }

        
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.addAll(tank.getBullets());
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) bullets.addAll(tank2.getBullets()); 
        for (TankAI tankAI : enemy) {
            bullets.addAll(tankAI.getBullets());
        }

        for (Bullet b : bullets) {
            if (b.isVisible()) {
                g.drawImage(b.getImage(), getXScaled(b.getX()), getYScaled(b.getY()), this);
            }
        }
        for (Block a : blocks) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), getXScaled(a.getX()), getYScaled(a.getY()), this);
            }
        }
        for (Animation e : animations) {
            if (e.isVisible()) {
                g.drawImage(e.getImage(), getXScaled(e.getX()), getYScaled(e.getY()), this);
            }
        }
        for (PowerUp p : powerUps) {
            if (p.isVisible()) {
                g.drawImage(p.getImage(), getXScaled(p.getX()), getYScaled(p.getY()), this);
            }
        }
    }

    /**
     * Draw the edge of the game
     *
     * @param g Graphics
     */
    private void drawEdge(Graphics g) {
        // Draw enemies
        drawEnemies(g, numEnemies);

        // Draw lives
        for (int i = 0; i < 2; i++) {
        	if (Options.getInstance().getOption(OptionsEnum.mode) != 2 && i == 1) continue;
            String ipText = "IP";
            int lives = tank.getHealth();
            if (i == 1) {
            	ipText = "IIP";
            	lives = tank2.getHealth();
            }
            Font font = loadFont();
            g.setFont(font);
            g.drawString(ipText, getXScaled(initX * ImageUtils.getDefaultBlockSize()),
            		getYScaled((17 + i * 2) * ImageUtils.getDefaultBlockSize()) - ((int)ImageUtils.getCurrent_scale_factor() * 2));

            Image liveIcon = ImageUtils.getImage(Images.lives); // imageInstance.getLives();
            g.drawImage(liveIcon, getXScaled(initX * ImageUtils.getDefaultBlockSize()), getYScaled((17 + i * 2) * ImageUtils.getDefaultBlockSize()), this);
            g.drawString(String.valueOf(lives < 0 ? 0 : lives), getXScaled((initX + 1) * ImageUtils.getDefaultBlockSize()),
            		getYScaled((18 + i * 2) * ImageUtils.getDefaultBlockSize()) - ((int)ImageUtils.getCurrent_scale_factor() * 3));

        }

        // Draw stages
        Image flagIcon = ImageUtils.getImage(Images.flagIcon); //imageInstance.getFlagIcon();
        g.drawImage(flagIcon, getXScaled(initX * ImageUtils.getDefaultBlockSize()), getYScaled(22 * ImageUtils.getDefaultBlockSize()), this);
        g.drawString(String.valueOf(stage), getXScaled((initX + 1) * ImageUtils.getDefaultBlockSize()), getYScaled(25 * ImageUtils.getDefaultBlockSize()));
    }

    /**
     * Draw the part that shows how many enemies left in the game on the edge of
     * the game board
     *
     * @param g Graphics
     * @param numEnemies number of enemies left in the game
     */
    private void drawEnemies(Graphics g, int numEnemies) {
        Image enemyIcon = ImageUtils.getImage(Images.enemyIcon); //imageInstance.getEnemyIcon();
        int count = 1;
        int initY = 4;
        for (int i = 0; i < numEnemies; i++) {
            switch (count) {
                case 0:
                    count = 1;
                    g.drawImage(enemyIcon, getXScaled((initX + 1) * ImageUtils.getDefaultBlockSize()), getYScaled(initY * ImageUtils.getDefaultBlockSize()), this);
                    initY++;
                    break;
                case 1:
                    count--;
                    g.drawImage(enemyIcon, getXScaled(initX * ImageUtils.getDefaultBlockSize()), getYScaled(initY * ImageUtils.getDefaultBlockSize()), this);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Decrease the number of enemies shown on the side bar of the board
     *
     * @param num the number of enemies that needs to be decreased
     */
    public static void decrementEnemies(int num) {
        numEnemies -= num;
    }

    /**
     * UpdatesSprites is used to call the various update calls.
     */
    public void updateSprites() {
        spawnTankAI();
        spawnPowerUp();
        updateTank();
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) updateTank2();
        updateTankAI();
        updateBullets();
        updateBlocks();
        updateAnimations();
        updateBlocks();
        updatePowerUps();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Menu.getMenuStatus() == false && pause == true) {
            return;
        }
        if (gameOver) {
            timer.stop();
            return;
        }
        updateSprites();
        checkCollisions();
        checkGameOver();

        try {
			nextLevel();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        repaint();
    }

    /**
     * Call initBoard to enter next Level when no enemy in the list.
     * @throws URISyntaxException 
     */
    public void nextLevel() throws URISyntaxException {
        if (enemy.isEmpty()) {
            if (stage == 35) {
                System.out.println("You win!");
                loadScoreBoard(theView);
            } else {
                stage += 1;
                numAI = 0;
                numEnemies = goal;
                clearBoard();

                initBlocks();
                CollisionUtility.loadCollisionUtility(blocks, animations);
                BoardUtility.loadBoardUtility(enemy, blocks, animations,
                                              powerUps,
                                              tank, tank2);
            }
        }
    }

    /**
     * Update animations on the board this includes
     * TankShield/Explosion/ExplodingTank/TankSpawn
     *
     * Animations are removed if vis is false. Otherwise animations are updated
     * via the updateAnimation method
     */
    public void updateAnimations() {
        BoardUtility.updateAnimations();
    }

    /**
     * Update blocks on the board this includes Base/Brick/Edge/River/Steel/Tree
     *
     * Blocks are removed if vis is false. Blocks that are types RIVER and BASE
     * they will be updated via the updateAnimation method
     */
    private void updateBlocks() {
        BoardUtility.updateBlocks();
    }

    /**
     * Updates the player tank.
     *
     * If the tank is visible it is moved
     */
    private void updateTank() {
        BoardUtility.updateTank();
    }

    private void updateTank2() {
        BoardUtility.updateTank2();
    }
    
    /**
     * Updates the tank AI.
     */
    private void updateTankAI() {
        for (TankAI tankAI : enemy) {
            if (tankAI.isVisible()) {
                if (System.currentTimeMillis() - tankAI.frozenStartTime > 5000 && tankAI.frozen) {
                    tankAI.frozen = false;
                }
                if ("easy".equals(tankAI.getDifficulty())) {
                    tankAI.actionEasy();
                } else if ("normal".equals(tankAI.getDifficulty())) {
                    tankAI.actionNormal(this.tank);
                } else if ("hard".equals(tankAI.getDifficulty())) {
                    tankAI.actionHard(this.tank);
                }
            }
        }
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i).vis == false) {
                enemy.remove(i);
            }
        }
    }

    /**
     * Spawn tank AI to reach the goal.
     */
    private void spawnTankAI() {
    	if (numAI >= goal) return;
    	boolean spawnNew = false;
    	if (enemy.size() < 4) spawnNew = true;
    	else if ((System.currentTimeMillis() - lastSpawnTime) > 6000) spawnNew = true; 
//        while (numAI < goal) {
            if (spawnNew) {
            	lastSpawnTime = System.currentTimeMillis(); 
                boolean powerUp;
                powerUp = (numAI % 4 == 1);
                if (numAI < 2) {
                    BoardUtility.spawnTankAI("easy", powerUp);
                } else if (numAI >= 2 && numAI < 6) {
                    BoardUtility.spawnTankAI("normal", powerUp);
                } else if (numAI >= 6) {
                    BoardUtility.spawnTankAI("hard", powerUp);
                }
                numAI++;
            } else {
                return;
            }
//        }
    }

    /**
     * Updates the powerUps on the board
     *
     * Unlike the other updateMethods, update for powerUps handles the collision
     * of a player tank and a powerUp PowerUps are removed if vis = false
     * otherwise they are updated via updateAnimations.
     *
     *
     */
    private void updatePowerUps() {
        BoardUtility.updatePowerUps();
    }

    private void spawnPowerUp() {
        BoardUtility.spawnPowerUp();
    }

    /**
     * Updates the bullets in the player Tank
     *
     * If the bullet vis = false they are removed. Otherwise they are moved with
     * move()
     */
    private void updateBulletsTank() {
        BoardUtility.updateBulletsTank();

    }

    private void updateBulletsTank2() {
        BoardUtility.updateBulletsTank2();

    }

    
    private void updateBulletsTankAI() {
        BoardUtility.updateBulletsTankAI();
    }
    
    /**
     * updates the bullets for both the player tank and enemyIcon Tanks
     */
    private void updateBullets() {
        updateBulletsTank();
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) updateBulletsTank2();
        updateBulletsTankAI();
    }

    /**
     * Check collisions between different sprite classes
     */
    public void checkCollisions() {
        BoardUtility.checkCollisions();
    }

    /**
     * Create end game information on the screen. After the "GAME OVER" label
     * shows on the screen, a score board of the entire game will be displayed
     *
     * @param g Graphics
     */
    public void endGame(Graphics g) {
        if (gameOver) {
            Timer gameOverTimer = new Timer(80, new ActionListener() {
                                        @Override
                                        public void actionPerformed(
                                                ActionEvent e) {
                                            yPos += direction;
                                            if (yPos == getHeight() / 2 - 14) {
                                                direction = 0;
                                            } else if (yPos > getHeight()) {
                                                yPos = getHeight();
                                            } else if (yPos < 0) {
                                                yPos = 0;
                                                direction *= -1;
                                            }
                                            for (Animation a : animations) {
                                                if (a.isVisible()) {
                                                    g.drawImage(a.getImage(), getXScaled(a.getX()), getYScaled(a.getY()), null);
                                                }
                                            }
                                            updateAnimations();
                                            for (Block a : blocks) {
                                                if (a.isVisible() && a.getType() == 3) {
                                                	a.updateAnimation();
                                                    g.drawImage(a.getImage(), getXScaled(a.getX()), getYScaled(a.getY()), null);
                                                }
                                            }
                                            repaint();
                                        }
                                    });
            gameOverTimer.setRepeats(true);
            gameOverTimer.setCoalesce(true);
            gameOverTimer.start();
            Font font = loadFont();
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("GAME OVER", (ImageUtils.getCurrent_board_width()) / 2 - 85 + ImageUtils.getDraw_from_x(), yPos);

            if (yPos == getHeight() / 2 - 14) {
                gameOverTimer.stop();
                Timer sorceBoardTimer = new Timer(3000, new ActionListener() {
                                              @Override
                                              public void actionPerformed(
                                                      ActionEvent e) {
                                                  loadScoreBoard(theView);
                                              }
                                          });
                sorceBoardTimer.setRepeats(false);
                sorceBoardTimer.start();
            }
        }
    }

    /**
     * Load the score board to the game board
     *
     * @param theView GameView that represents the frame of the game
     */
    public void loadScoreBoard(GameView theView) {
        theView.getGamePanel().removeAll();
        ScoreBoard scoreBoard = new ScoreBoard(theView);
        scoreBoard.setBackground(Color.BLACK);
        theView.getGamePanel().add(scoreBoard);
        scoreBoard.requestFocusInWindow();
        SoundUtility.statistics();
        theView.setVisible(true);
    }

    /**
     * Set the gameOver variable to true.
     */
    public static void setEndGame() {
        System.out.println("Game Over Played");
        SoundUtility.gameOver();
        gameOver = true;
    }

    /**
     * Restart the game and set gameOver to be false.
     */
    public static void restartGame() {
        gameOver = false;
    }

    /**
     * Clear the initialized variables on the board.
     */
    public void clearBoard() {

        animations = new ArrayList<>();
        blocks = new ArrayList<>();
        powerUps = new ArrayList<>();

        updateSprites();
        resetTankPosition(tank, 2);
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) CollisionUtility.resetTankPosition2(tank2, 2); 
        loadCollisionUtility(blocks, animations);

    }

    /**
     * Get the number of current stage
     *
     * @return stage an integer that represents the number of current stage
     */
    public static int getStage() {
        return stage;
    }

    /**
     * Tank key adapter. Override the methods in KeyAdapter to add events
     * handlers for the tanks
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
            if (Options.getInstance().getOption(OptionsEnum.mode) == 2) tank2.keyReleased(e); 
        }

        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
            if (Options.getInstance().getOption(OptionsEnum.mode) == 2) tank2.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!pause) {
                    SoundUtility.pause();
                }
                pause = !pause;

            }
        }
    }

}
