/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 29, 2016
 * Time: 7:23:00 PM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: BoardUtility
 * Description: A utility class for the board
 *
 * ****************************************
 */
package GameMain;

import SpriteClasses.Animation;
import SpriteClasses.Block;
import SpriteClasses.Bullet;
import SpriteClasses.ExplodingTank;
import SpriteClasses.ImageUtils;
import SpriteClasses.PowerUps.BombPowerUp;
import SpriteClasses.PowerUps.ClockPowerUp;
import SpriteClasses.PowerUps.PowerUp;
import SpriteClasses.PowerUps.ShieldPowerUp;
import SpriteClasses.PowerUps.StarPowerUp;
import SpriteClasses.PowerUps.TankPowerUp;
import SpriteClasses.Tank;
import SpriteClasses.Tank2;
import SpriteClasses.TankAI;
import SpriteClasses.TankShield;
import SpriteClasses.TankSpawn;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import GameMain.Options.OptionsEnum;

/**
 * A utility class for the board
 *
 * @author Adrian Berg
 */
public class BoardUtility {

    private static ArrayList<TankAI> enemy = new ArrayList<>();
    private static ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Animation> animations = new ArrayList<>();
    private static ArrayList<PowerUp> powerUps = new ArrayList<>();
    private static Tank tank;
    private static Tank2 tank2;

    /**
     * Constructor for the BoardUtility class
     *
     * @param enemy1 an array list that stores enemy tanks
     * @param blocks1 an array list that stores blocks on the board
     * @param animations1 an array list that stores different animations
     * @param powerUps1 an array list that stores different power-ups
     * @param tank1 the Tank class that represents the player
     */
    public static void loadBoardUtility(ArrayList<TankAI> enemy1,
                                        ArrayList<Block> blocks1,
                                        ArrayList<Animation> animations1,
                                        ArrayList<PowerUp> powerUps1, Tank tank1,
                                        Tank2 tank_) {
        enemy = enemy1;
        blocks = blocks1;
        animations = animations1;
        powerUps = powerUps1;
        tank = tank1;
        tank2 = tank_;
    }

    /**
     * Update power ups on the board.
     */
    public static void updatePowerUps() {
    	int i = 0;
    	while (i < powerUps.size()) {
            PowerUp p = powerUps.get(i);
            
            p.updateAnimation();
            BlockType type = BlockType.getTypeFromInt(p.getType());
            if (System.currentTimeMillis() - p.getLoadTime() > 10000) {
                powerUps.remove(i);
            } else {
                for (int k = 0; k < 2; k++) {
                	if (Options.getInstance().getOption(OptionsEnum.mode) != 2 && k == 1) continue; 
                    Rectangle r1 = tank.getBounds();
                    if (k == 1) r1 = tank2.getBounds();
                    Rectangle r2 = p.getBounds();

                    if (r1.intersects(r2)) {
                        powerUps.remove(i);
                        SoundUtility.powerupPick();
                        if (type.equals(BlockType.TANK)) {
                        	if (k == 0) tank.upHealth();
                        	else tank2.upHealth();
                        } else if (type.equals(BlockType.SHIELD)) {
                        	if (k == 0) {
                        		tank.shield = true;
                        		animations.add(new TankShield(tank, 1));
                        	} else {
                        		tank2.shield = true;
                        		animations.add(new TankShield(tank2, 1));
                        	}
                        } else if (type.equals(BlockType.SHOVEL)) {

                        } else if (type.equals(BlockType.STAR)) {
                        	if (k == 0) tank.upStarLevel();
                        	else tank2.upStarLevel();
                        } else if (type.equals(BlockType.CLOCK)) {
                            for (int x = 0; x < enemy.size(); x++) {
                                enemy.get(x).frozen = true;
                                enemy.get(x).frozenStartTime = System.currentTimeMillis();
                            }
                        } else if (type.equals(BlockType.BOMB)) {
                            for (int x = 0; x < enemy.size(); x++) {
                                enemy.get(x).vis = false;
                                for (TankAI ai : enemy) {
                                    CollisionUtility.incrementNum(ai);
                                }
                                Board.decrementEnemies(enemy.size());
                                animations.add(new ExplodingTank(enemy.get(x).x,
                                                                 enemy.get(x).y));
                            }
                        }
                    }            	
                }
                i++;
            }
        }

    }

    /**
     * Spawn random PowerUp when the given tank AI carries powerUp and is
     * destroyed.
     */
    public static void spawnPowerUp() {
        Random random = new Random();
        int randomPow = random.nextInt(5);
        if (CollisionUtility.powerUpX != 0 || CollisionUtility.powerUpY != 0) {
            switch (randomPow) {
                case 0:
                    powerUps.add(new BombPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                case 1:
                    powerUps.add(new ClockPowerUp(CollisionUtility.powerUpX,
                                                  CollisionUtility.powerUpY));
                    break;
                case 2:
                    powerUps.add(new ShieldPowerUp(CollisionUtility.powerUpX,
                                                   CollisionUtility.powerUpY));
                    break;
                case 3:
                    powerUps.add(new StarPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                case 4:
                    powerUps.add(new TankPowerUp(CollisionUtility.powerUpX,
                                                 CollisionUtility.powerUpY));
                    break;
                default:
                    break;
            }
            CollisionUtility.powerUpX = 0;
            CollisionUtility.powerUpY = 0;
        }
    }

    /**
     * Spawn Tank AI on the board.
     *
     * @param difficulty a string that represents the difficulty of the tank AI
     * @param powerUp a boolean that represents if the tank AI carries powerUp
     */
    public static void spawnTankAI(String difficulty, boolean powerUp) {
        Random random = new Random();
        int randomPos = random.nextInt(3);
        int randomType = random.nextInt(20);
        AiTankType type;
        if (randomType < 2) {
            type = AiTankType.armor;
        } else if (randomType >= 2 && randomType < 7) {
            type = AiTankType.power;
        } else if (randomType >= 8 && randomType < 13) {
            type = AiTankType.fast;
        } else {
            type = AiTankType.base;
        }
        if (randomPos == 0) {
            animations.add(new TankSpawn(2 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize()));
            TankAI AI = new TankAI(2 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize(), difficulty, type, powerUp);
            enemy.add(AI);
        } else if (randomPos == 1) {
            animations.add(new TankSpawn(14 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize()));
            TankAI AI = new TankAI(14 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize(), difficulty, type, powerUp);
            enemy.add(AI);
        } else {
            animations.add(new TankSpawn(26 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize()));
            TankAI AI = new TankAI(26 * ImageUtils.getDefaultBlockSize(), 1 * ImageUtils.getDefaultBlockSize(), difficulty, type, powerUp);
            enemy.add(AI);
        }
    }

    /**
     * Update bullets and tank AI on the board.
     */
    public static void updateBulletsTankAI() {
        for (TankAI tankAI : enemy) {
            ArrayList<Bullet> bullets = tankAI.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                if (b.isVisible()) {
                    b.move();
                } else if (b.isVisible() == false) {
                    bullets.remove(i);
                }
            }
        }
    }

    /**
     * Update bullets and tank on the Board.
     */
    public static void updateBulletsTank() {
        ArrayList<Bullet> bullets = tank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.isVisible()) {
                b.move();
            } else if (b.isVisible() == false) {
                bullets.remove(i);
            }
        }
    }
    
    public static void updateBulletsTank2() {
        ArrayList<Bullet> bullets = tank2.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.isVisible()) {
                b.move();
            } else if (b.isVisible() == false) {
                bullets.remove(i);
            }
        }
    }

    /**
     * Update blocks on the Board.
     */
    public static void updateBlocks() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            BlockType type = BlockType.getTypeFromInt(b.getType());
            if (type.equals(BlockType.RIVER)) {
                b.updateAnimation();
            } else if (type.equals(BlockType.BASE)) {
                b.updateAnimation();
            }
            if (b.isVisible() == false) {
                blocks.remove(i);
            }
        }
    }

    /**
     * Update animations on the Board.
     */
    public static void updateAnimations() {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).vis == false) {
                animations.remove(i);
            } else {
                animations.get(i).updateAnimation();
            }
        }
    }

    /**
     * Update tank on the Board.
     */
    public static void updateTank() {
        if (tank.isVisible()) {
            tank.move();
        }
    }
    
    public static void updateTank2() {
        if (tank2.isVisible()) {
            tank2.move();
        }
    }

    /**
     * Check for collisions on the board.
     */
    public static void checkCollisions() {
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.addAll(tank.getBullets());
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) bullets.addAll(tank2.getBullets()); 
        for (TankAI tankAI : enemy) {
            bullets.addAll(tankAI.getBullets());
        }
        CollisionUtility.checkCollisionBulletsBullets(bullets);
        CollisionUtility.checkCollisionBulletsBlocks(bullets, blocks);
        CollisionUtility.checkCollisionBulletsTank(bullets, tank);
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) CollisionUtility.checkCollisionBulletsTank2(bullets, tank2);
        CollisionUtility.checkCollisionBulletsTankAI(bullets, enemy);
        CollisionUtility.checkCollisionTankTankAI(enemy, tank);
        if (Options.getInstance().getOption(OptionsEnum.mode) == 2) CollisionUtility.checkCollisionTankTankAI2(enemy, tank2);

    }

	public static void setTank2(Tank2 tank2) {
		BoardUtility.tank2 = tank2;
	}
    
    

}
