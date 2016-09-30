/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: May 1, 2016
 * Time: 11:19:03 PM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: CollisionUtilityTest
 * Description:
 *
 * ****************************************
 */
package GameMain;

import SpriteClasses.Animation;
import SpriteClasses.Block;
import SpriteClasses.Brick;
import SpriteClasses.Bullet;
import SpriteClasses.Steel;
import SpriteClasses.Tank;
import SpriteClasses.TankAI;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Berg
 */
public class CollisionUtilityTest {
    ArrayList<Block> inblocks;
    ArrayList<Animation> inexplosion;

    public CollisionUtilityTest() {

    }

    @Before
    public void setUp() {
        inblocks = new ArrayList<>();
        inexplosion = new ArrayList<>();
        Brick brick = new Brick(10, 10);
        inblocks.add(brick);
        CollisionUtility.loadCollisionUtility(inblocks, inexplosion);
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of checkCollisionTankBlocks method, of class CollisionUtility.
     */
    @Test
    public void testCheckCollisionTankBlocks() {
        System.out.println("checkCollisionTankBlocks");
        Rectangle r3 = new Rectangle(10, 10, 10, 10);
        boolean result = CollisionUtility.checkCollisionTankBlocks(r3);
        assertEquals(true, result);
        inblocks.remove(0);
        result = CollisionUtility.checkCollisionTankBlocks(r3);
        assertEquals(false, result);
    }

    /**
     * Test of checkCollisionBulletsBlocks method, of class CollisionUtility.
     */
    @Test
    public void testCheckCollisionBulletsBlocks() {

        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(10, 10, 0, true));
        assertEquals(bullets.get(0).vis, true);
        CollisionUtility.checkCollisionBulletsBlocks(bullets, inblocks);
        assertEquals(bullets.get(0).vis, false);
        assertEquals(inblocks.get(0).vis, false);
        inblocks.add(new Steel(20, 20));
        bullets.add(new Bullet(20, 20, 0, false));
        CollisionUtility.checkCollisionBulletsBlocks(bullets, inblocks);
        assertEquals(bullets.get(1).vis, false);
        assertEquals(inblocks.get(1).vis, true);

    }

    /**
     * Test of checkCollisionBulletsTank method, of class CollisionUtility.
     */
    @Test
    public void testCheckCollisionBulletsTank() {
        Tank tank = new Tank(10, 10, 2);
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(10, 10, 0, true));
        CollisionUtility.checkCollisionBulletsTank(bullets, tank);
        assertEquals(tank.x, 10 * 16);
        assertEquals(tank.y, (Map.level0.length - 3) * 16);
        assertEquals(tank.starLevel, 0);
        assertEquals(tank.shield, true);
        tank = new Tank(20, 20, 2);
        bullets.add(new Bullet(10, 10, 0, false));
        CollisionUtility.checkCollisionBulletsTank(bullets, tank);
        assertNotSame(tank.x, 10 * 16);

    }

    /**
     * Test of checkCollisionTankTankAI method, of class CollisionUtility.
     */
    @Test
    public void testCheckCollisionTankTankAI() {
        ArrayList<TankAI> TankAIs = new ArrayList<>();
        TankAI tankAI;
        tankAI = new TankAI(10, 10, "easy", "basic", true);
        TankAIs.add(tankAI);
        Tank tank = new Tank(10, 10, 2);
        tank.shield = true;
        CollisionUtility.checkCollisionTankTankAI(TankAIs, tank);
        assertEquals(TankAIs.get(0).vis, false);
        tank.shield = false;
        TankAIs.get(0).vis = true;
        CollisionUtility.checkCollisionTankTankAI(TankAIs, tank);
        assertEquals(TankAIs.get(0).vis, true);

    }
}
