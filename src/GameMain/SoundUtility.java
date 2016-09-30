/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016
 *
 * Name: Tongyu Yang, Peter Unrein, Hung Giang, Adrian Berg
 * Date: Apr 20, 2016
 * Time: 10:44:19 AM
 *
 * Project: csci205FinalProject
 * Package: GameMain
 * File: SoundUtility
 * Description: Utility class for sound
 *
 * ****************************************
 */
package GameMain;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Utility class for sound
 *
 * @author pvu001
 */
public class SoundUtility {
    private static Clip bulletBrickSE, bulletTankSE;
    private static Clip fireSE;
    private static Clip explosion1SE, explosion2SE;
    private static Clip startStageSE;
    private static Clip pauseSE;
    private static Clip powerupAppearSE;
    private static Clip powerupPickSE;
    private static Clip gameoverSE;
    private static Clip statisticsSE;

    private static boolean initialized = false;

    /**
     * Load different sound files
     */
    public static void initialize() {
        System.out.println("INITIALIZE");
        try {
            File bBse = new File("sound/bullet_hit_2.wav");
            bulletBrickSE = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(bBse);
            bulletBrickSE.open(ais);
            bulletBrickSE.setFramePosition(bulletBrickSE.getFrameLength());

            File bTse = new File("sound/bullet_hit_1.wav");
            bulletTankSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(bTse);
            bulletTankSE.open(ais);
            bulletTankSE.setFramePosition(bulletTankSE.getFrameLength());

            File fSE = new File("sound/bullet_shot.wav");
            fireSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(fSE);
            fireSE.open(ais);
            fireSE.setFramePosition(fireSE.getFrameLength());

            File ex1 = new File("sound/explosion_1.wav");
            explosion1SE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(ex1);
            explosion1SE.open(ais);
            explosion1SE.setFramePosition(explosion1SE.getFrameLength());

            File ex2 = new File("sound/explosion_2.wav");
            explosion2SE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(ex2);
            explosion2SE.open(ais);
            explosion2SE.setFramePosition(explosion2SE.getFrameLength());

            File stageStart = new File("sound/stage_start.wav");
            startStageSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(stageStart);
            startStageSE.open(ais);
            startStageSE.setFramePosition(startStageSE.getFrameLength());

            File pause = new File("sound/pause.wav");
            pauseSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(pause);
            pauseSE.open(ais);
            pauseSE.setFramePosition(pauseSE.getFrameLength());

            File powerUpAppear = new File("sound/powerup_appear.wav");
            powerupAppearSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(powerUpAppear);
            powerupAppearSE.open(ais);
            powerupAppearSE.setFramePosition(powerupAppearSE.getFrameLength());

            File powerUpPickup = new File("sound/powerup_pick.wav");
            powerupPickSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(powerUpPickup);
            powerupPickSE.open(ais);
            powerupPickSE.setFramePosition(powerupPickSE.getFrameLength());

            File game_over = new File("sound/game_over.wav");
            gameoverSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(game_over);
            gameoverSE.open(ais);
            gameoverSE.setFramePosition(gameoverSE.getFrameLength());

            File statistics = new File("sound/statistics_1.wav");
            statisticsSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(statistics);
            statisticsSE.open(ais);
            statisticsSE.setFramePosition(statisticsSE.getFrameLength());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
                                                               null, ex);
        }
        initialized = true;
    }

    /**
     * Play sound for bullet hits brick
     */
    public static void BulletHitBrick() {
        if (initialized) {
            bulletBrickSE.loop(1);
        } else {
            initialize();
            bulletBrickSE.loop(1);
        }
    }

    /**
     * Play sound for bullet hits tank
     */
    public static void BulletHitTank() {
        if (initialized) {
            bulletTankSE.loop(1);
        } else {
            initialize();
            bulletTankSE.loop(1);
        }

    }

    /**
     * Play sound for firing a bullet
     */
    public static void fireSound() {
        if (initialized) {
            fireSE.loop(1);
        } else {
            initialize();
            fireSE.loop(1);
        }
    }

    /**
     * Play sound for explosion
     */
    public static void explosion1() {
        if (initialized) {
            explosion1SE.loop(1);
        } else {
            initialize();
            explosion1SE.loop(1);
        }
    }

    /**
     * Play sound for explosion
     */
    public static void explosion2() {
        if (initialized) {
            explosion2SE.loop(1);
        } else {
            initialize();
            explosion2SE.loop(1);
        }
    }

    /**
     * Play sound for the start stage of the game
     */
    public static void startStage() {
        if (initialized) {
            startStageSE.loop(1);
        } else {
            initialize();
            startStageSE.loop(1);
        }
    }

    /**
     * Play sound for the pause
     */
    public static void pause() {
        if (initialized) {
            pauseSE.loop(1);
        } else {
            System.out.println("pause");
            initialize();
            pauseSE.loop(1);
        }
    }

    /**
     * Play sound for powerUp pick
     */
    public static void powerupPick() {
        if (initialized) {
            powerupPickSE.loop(1);
        } else {
            initialize();
            powerupPickSE.loop(1);
        }
    }

    /**
     * Play sound for gameOver
     */
    public static void gameOver() {
        if (initialized) {
            gameoverSE.loop(1);
        } else {
            initialize();
            gameoverSE.loop(1);
        }
    }

    /**
     * Play sound for the score screen
     */
    public static void statistics() {
        if (initialized) {
            statisticsSE.loop(1);
        } else {
            initialize();
            statisticsSE.loop(1);
        }
    }
}
