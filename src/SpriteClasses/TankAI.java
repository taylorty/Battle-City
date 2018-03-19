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

import GameMain.AiTankType;
import GameMain.CollisionUtility;
import SpriteClasses.ImageUtils.Images;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

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
	
	
//    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
//    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
	private AiTankType type;
    private ArrayList<Bullet> bullets;
    private boolean powerUp;
    private int dx, dy;
    public int direction = 2;
    private int health;
    private String difficulty;
//    private String type;
    private int dirTimer = 0;
    private int dirUpdateInterval;
    private int fireTimer = 0;
    private int fireUpdateInterval;
    private double speedConst;
    public boolean frozen = false;
    public long frozenStartTime;
//    private String imageUp;
//    private String imageDown;
//    private String imageLeft;
//    private String imageRight;

    public TankAI(int x, int y, String difficulty, AiTankType type, boolean powerUp) {
    	super(x,y,null);
        this.vis = true;
        this.width = 32;
        this.height = 32;
    	switch (type) {
		case armor: this.imageType = Images.aiTank_armor_down;
			break;
		case base: this.imageType = Images.aiTank_basic_down;
			break;
		case fast: this.imageType = Images.aiTank_fast_down;
			break;
		case power: this.imageType = Images.aiTank_power_down;
			break;
    	}
        bullets = new ArrayList<>();
        this.vis = true;
        this.powerUp = powerUp;
        this.difficulty = difficulty;
        this.type = type;
        this.setUp();
        this.dirUpdate();
//        this.imageSetUp();
    }

    /**
     * setUp() is a helper function. It will handle setting up health, speed and
     * fire rate for the AI, depending on the type of the AI.
     *
     * @author Hung Giang
     */
    private void setUp() {
    	switch (this.type) {
		case armor:
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
			break;
		case base:
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
			break;
		case fast:
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
			break;
		case power:
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
			break;
    	}
    }

    /**
     * imageSetUp() is a helper function. It will handle setting up the image
     * for different direction depending on the type of the AI.
     *
     * @author Hung Giang
     */
//    private void imageSetUp() {
//        if ("basic".equals(this.type)) {
//            loadImage("./Battle-City/image/tank_basic_up.png");
//            getImageDimensions();
//            this.imageUp = "./Battle-City/image/tank_basic_up.png";
//            this.imageDown = "./Battle-City/image/tank_basic_down.png";
//            this.imageLeft = "./Battle-City/image/tank_basic_left.png";
//            this.imageRight = "./Battle-City/image/tank_basic_right.png";
//        } else if ("armor".equals(this.type)) {
//            loadImage("./Battle-City/image/tank_armor_up.png");
//            getImageDimensions();
//            this.imageUp = "./Battle-City/image/tank_armor_up.png";
//            this.imageDown = "./Battle-City/image/tank_armor_down.png";
//            this.imageLeft = "./Battle-City/image/tank_armor_left.png";
//            this.imageRight = "./Battle-City/image/tank_armor_right.png";
//        } else if ("power".equals(this.type)) {
//            loadImage("./Battle-City/image/tank_power_up.png");
//            getImageDimensions();
//            this.imageUp = "./Battle-City/image/tank_power_up.png";
//            this.imageDown = "./Battle-City/image/tank_power_down.png";
//            this.imageLeft = "./Battle-City/image/tank_power_left.png";
//            this.imageRight = "./Battle-City/image/tank_power_right.png";
//        } else if ("fast".equals(this.type)) {
//            loadImage("./Battle-City/image/tank_fast_up.png");
//            getImageDimensions();
//            this.imageUp = "./Battle-City/image/tank_fast_up.png";
//            this.imageDown = "./Battle-City/image/tank_fast_down.png";
//            this.imageLeft = "./Battle-City/image/tank_fast_left.png";
//            this.imageRight = "./Battle-City/image/tank_fast_right.png";
//        }
//    }

//    @Override
//    public int getX() {
//        return x;
//    }
//
//    @Override
//    public int getY() {
//        return y;
//    }

//    @Override
//    public Image getImage() {
//        return image;
//    }

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

        if (x > ImageUtils.getDefaultBlockSize() * 33 - width) {
            x = ImageUtils.getDefaultBlockSize() * 33 - width;
        }

        if (y > ImageUtils.getDefaultBlockSize() * 28 - height) {
            y = ImageUtils.getDefaultBlockSize() * 28 - height;
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
                aBullet = new Bullet(x + width / 3, y, 0, true, false);
                break;
            case 1:
                aBullet = new Bullet(x + width, y + height / 3, 1, true, false);
                break;
            case 2:
                aBullet = new Bullet(x + width / 3, y + height, 2, true, false);
                break;
            default:
                aBullet = new Bullet(x, y + height / 3, 3, true, false);
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

    private void upateImageOnType() {
        switch (this.direction) {
        	case 0:
        		switch (this.type) {
        			case base : updateImage(Images.aiTank_basic_up);
        				break;
        			case power : updateImage(Images.aiTank_power_up);
    					break;
        			case fast : updateImage(Images.aiTank_fast_up);
    					break;
        			case armor : updateImage(Images.aiTank_armor_up);
        				break;
        		}
        		break;
        	case 1:
        		switch (this.type) {
        			case base : updateImage(Images.aiTank_basic_right);
    					break;
        			case power : updateImage(Images.aiTank_power_right);
						break;
        			case fast : updateImage(Images.aiTank_fast_right);
						break;
        			case armor : updateImage(Images.aiTank_armor_right);
    					break;
        		}
        		break;
        	case 2:
        		switch (this.type) {
    				case base : updateImage(Images.aiTank_basic_down);
						break;
    				case power : updateImage(Images.aiTank_power_down);
						break;
    				case fast : updateImage(Images.aiTank_fast_down);
						break;
    				case armor : updateImage(Images.aiTank_armor_down);
    					break;
        		}
        		break;
        case 3:
    			switch (this.type) {
					case base : updateImage(Images.aiTank_basic_left);
						break;
					case power : updateImage(Images.aiTank_power_left);
						break;
					case fast : updateImage(Images.aiTank_fast_left);
						break;
					case armor : updateImage(Images.aiTank_armor_left);
						break;
    			}
    			break;
        }
    }
    
    /**
     * dirUpdate() handles updating image and acceleration of the AI according
     * to direction
     *
     * @author Hung Giang
     */
    private void dirUpdate() {
//        ImageIcon ii;
        if (frozen) {
            this.dx = 0;
            this.dy = 0;
        } else {
            switch (this.direction) {
                case 0:
//                    ii = new ImageIcon(this.imageUp);
//                    image = ii.getImage();
                    upateImageOnType();
                    this.dx = 0;
                    this.dy = (int) (-1 * this.speedConst);
                    x = (int)(Math.round(((double)x / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
                    break;
                case 1:
//                    ii = new ImageIcon(this.imageRight);
//                    image = ii.getImage();
                	upateImageOnType();
                    this.dx = (int) (1 * this.speedConst);
                    this.dy = 0;
                    y = (int)(Math.round(((double)y / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
                    break;
                case 2:
//                    ii = new ImageIcon(this.imageDown);
//                    image = ii.getImage();
                	upateImageOnType();
                    this.dx = 0;
                    this.dy = (int) (1 * this.speedConst);
                    x = (int)(Math.round(((double)x / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
                    break;
                case 3:
//                    ii = new ImageIcon(this.imageLeft);
//                    image = ii.getImage();
                	upateImageOnType();
                    this.dx = (int) (-1 * this.speedConst);
                    this.dy = 0;
                    y = (int)(Math.round(((double)y / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
                    break;
            }
        }
    }

    /**
     * Get the type of the Enemy tank
     *
     * @return type
     */
    public AiTankType getType() {
        return type;
    }
}
