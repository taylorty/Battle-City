/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang
 * Date: Apr 16, 2016
 * Time: 10:09:41 PM
 *
 * Project: csci205FinalProject
 * Package: scratch
 * File: TankAI
 * Description: Tank AI class
 *
 * ****************************************
 */
package SpriteClasses;

import GameMain.CollisionUtility;
import GameMain.Map;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * TankAI extends Sprite. TankAI represents the enemy in the game. TankAI has an
 * array of bullets and is capable of moving and firing bullets depending on
 * difficulties. TankAI has 4 different types that is upgraded in several ways.
 * TankAI can also give player power up.
 *
 * @param int x represents the starting x location in pixels
 * @param int y represents the starting y location in pixels
 * @param String difficulty represents the difficulty
 * @param String type represents the type of the AI
 * @param boolean powerUp indicates if the AI will give player powerUp or not
 * @author Hung Giang
 */
public class TankAI extends Sprite {
    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
    private ArrayList<Bullet> bullets;
    private boolean powerUp;
    private int dx, dy;
    public int direction;
    private int health;
    private String difficulty;
    private String type;
    private int dirTimer = 0;
    private int dirUpdateInterval;
    private int fireTimer = 0;
    private int fireUpdateInterval;
    private double speedConst;
    public boolean frozen = false;
    public long frozenStartTime;
    private String imageUp;
    private String imageDown;
    private String imageLeft;
    private String imageRight;

    public TankAI(int x, int y, String difficulty, String type, boolean powerUp) {
        super(x, y);
        bullets = new ArrayList<>();
        direction = 0;
        this.vis = true;
        this.powerUp = powerUp;
        this.difficulty = difficulty;
        this.type = type;
        this.setUp();
        this.imageSetUp();
    }

    /**
     * setUp() is a helper function. It will handle setting up health, speed and
     * fire rate for the AI, depending on the type of the AI.
     *
     * @author Hung Giang
     */
    private void setUp() {
        if ("basic".equals(this.type)) {
            this.health = 1;
            this.speedConst = 1;
            if (difficulty.equals("easy")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 80;
            } else if (difficulty.equals("normal")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 75;
            } else if (difficulty.equals("hard")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 70;
            }
        } else if ("armor".equals(this.type)) {
            this.health = 4;
            this.speedConst = 1;
            if (difficulty.equals("easy")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 80;
            } else if (difficulty.equals("normal")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 75;
            } else if (difficulty.equals("hard")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 70;
            }
        } else if ("power".equals(this.type)) {
            this.health = 1;
            this.speedConst = 1;
            if (difficulty.equals("easy")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 40;
            } else if (difficulty.equals("normal")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 35;
            } else if (difficulty.equals("hard")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 30;
            }
        } else if ("fast".equals(this.type)) {
            this.health = 1;
            this.speedConst = 2;
            if (difficulty.equals("easy")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 80;
            } else if (difficulty.equals("normal")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 75;
            } else if (difficulty.equals("hard")) {
                dirUpdateInterval = 30;
                fireUpdateInterval = 70;
            }
        }
    }

    /**
     * imageSetUp() is a helper function. It will handle setting up the image
     * for different direction depending on the type of the AI.
     *
     * @author Hung Giang
     */
    private void imageSetUp() {
        if ("basic".equals(this.type)) {
            loadImage("image/tank_basic_up.png");
            getImageDimensions();
            this.imageUp = "image/tank_basic_up.png";
            this.imageDown = "image/tank_basic_down.png";
            this.imageLeft = "image/tank_basic_left.png";
            this.imageRight = "image/tank_basic_right.png";
        } else if ("armor".equals(this.type)) {
            loadImage("image/tank_armor_up.png");
            getImageDimensions();
            this.imageUp = "image/tank_armor_up.png";
            this.imageDown = "image/tank_armor_down.png";
            this.imageLeft = "image/tank_armor_left.png";
            this.imageRight = "image/tank_armor_right.png";
        } else if ("power".equals(this.type)) {
            loadImage("image/tank_power_up.png");
            getImageDimensions();
            this.imageUp = "image/tank_power_up.png";
            this.imageDown = "image/tank_power_down.png";
            this.imageLeft = "image/tank_power_left.png";
            this.imageRight = "image/tank_power_right.png";
        } else if ("fast".equals(this.type)) {
            loadImage("image/tank_fast_up.png");
            getImageDimensions();
            this.imageUp = "image/tank_fast_up.png";
            this.imageDown = "image/tank_fast_down.png";
            this.imageLeft = "image/tank_fast_left.png";
            this.imageRight = "image/tank_fast_right.png";
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public boolean hasPowerUp() {
        return powerUp;
    }

    public void decreaseHP() {
        this.health -= 1;
    }

    public int getHealth() {
        return health;
    }

    /**
     * actionEasy() handles action of AI with easy difficulty.
     *
     * @author Hung Giang
     */
    public void actionEasy() {
        if (this.dirTimer >= this.dirUpdateInterval) {
            randomDir();
            this.dirTimer = 0;
        } else {
            this.dirTimer++;
        }
        this.move();
        if (this.fireTimer >= this.fireUpdateInterval) {
            this.fire();
            this.fireTimer = 0;
        } else {
            this.fireTimer++;
        }
    }

    /**
     * actionNormal() handles action of AI with normal difficulty.
     *
     * @param Tank tank: the player tank
     * @author Hung Giang
     */
    public void actionNormal(Tank tank) {
        Random randomDir = new Random();
        if (this.dirTimer >= this.dirUpdateInterval) {
            int random = randomDir.nextInt(20);
            if (random % 8 == 1) {
                toEagleDir();
            } else if (random % 4 == 0) {
                randomDir();
            } else {
                toTankDir(tank);
            }
            this.dirTimer = 0;
        } else {
            this.dirTimer++;
        }
        this.move();
        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);

        if (CollisionUtility.checkCollisionTankBlocks(theTank) == true) {
            if (randomDir.nextBoolean() && this.fireTimer < 3) {
                this.fire();
                this.fireTimer++;
            }
        }
        if (this.fireTimer >= this.fireUpdateInterval) {
            this.fire();
            this.fireTimer = 0;
        } else {
            this.fireTimer++;
        }
    }

    /**
     * actionHard() handles action of AI with hard difficulty.
     *
     * @param Tank tank: the player tank
     * @author Hung Giang
     */
    public void actionHard(Tank tank) {
        Random randomDir = new Random();
        if (this.dirTimer >= this.dirUpdateInterval) {
            int random = randomDir.nextInt(7);
            if (random % 5 == 0) {
                toEagleDir();
            } else if (random % 6 == 1) {
                randomDir();
            } else {
                toTankDir(tank);
            }
            this.dirTimer = 0;
        } else {
            this.dirTimer++;
        }
        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);
        this.move();
        if (CollisionUtility.checkCollisionTankBlocks(theTank) == true) {
            if (randomDir.nextBoolean() && this.fireTimer < 3) {
                this.fire();
                this.fireTimer++;
            }
        }
        if (this.fireTimer >= this.fireUpdateInterval) {
            this.fire();
            this.fireTimer = 0;
        } else {
            this.fireTimer++;
        }
    }

    /**
     * move() handles moving of the AI.
     *
     * @author Hung Giang
     */
    public void move() {
        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);

        if (CollisionUtility.checkCollisionTankBlocks(theTank) == false) {
            x += dx;
            y += dy;
        }

        if (x > BOARD_WIDTH - width) {
            x = BOARD_WIDTH - width;
        }

        if (y > BOARD_HEIGHT - height) {
            y = BOARD_HEIGHT - height;
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    /**
     * fire() handle firing bullet for the AI.
     *
     * @author Hung Giang
     */
    public void fire() {
        Bullet aBullet;
        switch (direction) {
            case 0:
                aBullet = new Bullet(x + width / 3, y, 0, true);
                break;
            case 1:
                aBullet = new Bullet(x + width, y + height / 3, 1, true);
                break;
            case 2:
                aBullet = new Bullet(x + width / 3, y + height, 2, true);
                break;
            default:
                aBullet = new Bullet(x, y + height / 3, 3, true);
                break;
        }
        if (frozen) {
        } else {
            bullets.add(aBullet);
        }
//        SoundUtility.fireSound();
    }

    /**
     * randomDir() handle finding the direction randomly.
     *
     * @author Hung Giang
     */
    public void randomDir() {
        Random randomDir = new Random();
        this.direction = randomDir.nextInt(4);
        dirUpdate();
    }

    /**
     * toTankDir() handle finding direction to the player's tank.
     *
     * @param Tank tank: the player tank.
     * @author Hung Giang
     */
    public void toTankDir(Tank tank) {
        int tankX = tank.getX();
        int tankY = tank.getY();
        Random randomDir = new Random();
        if (randomDir.nextBoolean()) {
            if (this.getY() > tankY) {
                this.direction = 0;
            } else {
                this.direction = 2;
            }
        } else if (this.getX() > tankX) {
            this.direction = 3;
        } else if (this.getX() < tankX) {
            this.direction = 1;
        } else {
            this.direction = 3;
        }
        dirUpdate();
    }

    /**
     * toEagleDir() handles finding direction to the base.
     *
     * @author Hung Giang
     */
    public void toEagleDir() {
        if (this.getX() > 14 * 16) {
            this.direction = 3;
        } else if (this.getX() < 14 * 16) {
            this.direction = 1;
        } else {
            this.direction = 2;
        }
        dirUpdate();
    }

    /**
     * dirUpdate() handles updating image and acceleration of the AI according
     * to direction
     *
     * @author Hung Giang
     */
    private void dirUpdate() {
        ImageIcon ii;
        if (frozen) {
            this.dx = 0;
            this.dy = 0;
        } else {
            switch (this.direction) {
                case 0:
                    ii = new ImageIcon(this.imageUp);
                    image = ii.getImage();
                    this.dx = (int) (0 * this.speedConst);
                    this.dy = (int) (-1 * this.speedConst);
                    break;
                case 1:
                    ii = new ImageIcon(this.imageRight);
                    image = ii.getImage();
                    this.dx = (int) (1 * this.speedConst);
                    this.dy = (int) (0 * this.speedConst);
                    break;
                case 2:
                    ii = new ImageIcon(this.imageDown);
                    image = ii.getImage();
                    this.dx = (int) (0 * this.speedConst);
                    this.dy = (int) (1 * this.speedConst);
                    break;
                case 3:
                    ii = new ImageIcon(this.imageLeft);
                    image = ii.getImage();
                    this.dx = (int) (-1 * this.speedConst);
                    this.dy = (int) (0 * this.speedConst);
                    break;
            }
        }
    }

    /**
     * Get the type of the Enemy tank
     *
     * @return type
     */
    public String getType() {
        return type;
    }
}
