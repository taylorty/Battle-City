/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: May 1, 2016
 * Time: 8:52:20 PM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: BoardUtilityTest
 * Description:
 *
 * ****************************************
 */
package GameMain;

import SpriteClasses.Animation;
import SpriteClasses.Block;
import SpriteClasses.Brick;
import SpriteClasses.ExplodingTank;
import SpriteClasses.PowerUps.BombPowerUp;
import SpriteClasses.PowerUps.ClockPowerUp;
import SpriteClasses.PowerUps.PowerUp;
import SpriteClasses.PowerUps.ShieldPowerUp;
import SpriteClasses.PowerUps.StarPowerUp;
import SpriteClasses.PowerUps.TankPowerUp;
import SpriteClasses.Tank;
import SpriteClasses.TankAI;
import SpriteClasses.TankSpawn;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Berg
 */
public class BoardUtilityTest {
    ArrayList<TankAI> enemy;
    ArrayList<Block> blocks;
    ArrayList<Animation> animations;
    ArrayList<PowerUp> powerUps;
    Tank tank;
    TankAI tankAI;
    Brick brick;
    TankSpawn ts;

    public BoardUtilityTest() {
    }

    @Before
    public void setUp() {

        enemy = new ArrayList<>();
        blocks = new ArrayList<>();
        animations = new ArrayList<>();
        powerUps = new ArrayList<>();
        tank = new Tank(10, 10, 3);
        tankAI = new TankAI(10, 10, "hard", "basic", true);
        brick = new Brick(10, 10);
        ts = new TankSpawn(10, 10);
        enemy.add(tankAI);
        blocks.add(brick);
        powerUps.add(new TankPowerUp(10, 10));
        BoardUtility.loadBoardUtility(enemy, blocks, animations, powerUps, tank);

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of updatePowerUps method, of class BoardUtility.
     */
    @Test
    public void testUpdatePowerUps() {

        BoardUtility.updatePowerUps();
        assertEquals(3, tank.getHealth());
        powerUps.add(new TankPowerUp(10, 10));
        assertEquals(3, tank.getHealth());
        BoardUtility.updatePowerUps();
        assertEquals(4, tank.getHealth());

        assertEquals(false, tank.shield);
        powerUps.add(new ShieldPowerUp(10, 10));
        BoardUtility.updatePowerUps();
        assertEquals(true, tank.shield);

        assertEquals(0, tank.getStarLevel());
        powerUps.add(new StarPowerUp(10, 10));
        BoardUtility.updatePowerUps();
        assertEquals(1, tank.getStarLevel());

        powerUps.add(new ClockPowerUp(10, 10));
        BoardUtility.updatePowerUps();
        assertEquals(true, enemy.get(0).frozen);

        powerUps.add(new BombPowerUp(10, 10));
        BoardUtility.updatePowerUps();
        assertEquals(false, enemy.get(0).vis);
    }

    /**
     * Test of spawnPowerUp method, of class BoardUtility.
     */
    @Test
    public void testSpawnPowerUp() {
        CollisionUtility.powerUpX = 1;
        CollisionUtility.powerUpY = 1;
        BoardUtility.spawnPowerUp();
        assertEquals(1, powerUps.get(1).x);
        assertEquals(1, powerUps.get(1).y);

    }

    /**
     * Test of spawnTankAI method, of class BoardUtility.
     */
    @Test
    public void testSpawnTankAI() {
        assertEquals(1, enemy.size());
        BoardUtility.spawnTankAI("easy", true);
        assertEquals(2, enemy.size());
    }

    /**
     * Test of updateBulletsTankAI method, of class BoardUtility.
     */
    @Test
    public void testUpdateBulletsTankAI() {
        tankAI.fire();
        BoardUtility.updateBulletsTankAI();
        assertEquals(true, tankAI.getBullets().get(0).vis);
        tankAI.getBullets().get(0).vis = false;
        BoardUtility.updateBulletsTankAI();
        assertEquals(0, tank.getBullets().size());

    }

    /**
     * Test of updateBulletsTank method, of class BoardUtility.
     */
    @Test
    public void testUpdateBulletsTank() {
        System.out.println("updateBulletsTank");
        tank.fire();
        assertEquals(tank.getBullets().get(0).x, 18);
        BoardUtility.updateBulletsTank();
        assertEquals(true, tank.getBullets().get(0).vis);
        tank.getBullets().get(0).vis = false;
        BoardUtility.updateBulletsTank();
        assertEquals(0, tank.getBullets().size());

    }

    /**
     * Test of updateBlocks method, of class BoardUtility.
     */
    @Test
    public void testUpdateBlocks() {
        assertEquals(blocks.size(), 1);
        blocks.get(0).vis = false;
        BoardUtility.updateBlocks();
        assertEquals(blocks.size(), 0);

    }

    /**
     * Test of updateAnimations method, of class BoardUtility.
     */
    @Test
    public void testUpdateAnimations() {
        animations.add(new ExplodingTank(10, 10));
        assertEquals(animations.size(), 1);
        animations.get(0).vis = false;
        BoardUtility.updateAnimations();
        assertEquals(animations.size(), 0);

    }

}
