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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Utility class for sound
 *
 * @author pvu001
 */
public class SoundUtility {
//    private static Clip bulletBrickSE, bulletTankSE;
//    private static Clip fireSE;
//    private static Clip explosion1SE, explosion2SE;
//    private static Clip startStageSE;
//    private static Clip pauseSE;
//    private static Clip powerupAppearSE;
//    private static Clip powerupPickSE;
//    private static Clip gameoverSE;
//    private static Clip statisticsSE;

    private static boolean initialized = false;
    private static final String prefix = "sound/";
    
    @SuppressWarnings("serial")
	private static final Map<String, Clip> musicClips = new HashMap<String, Clip>() {
    	{
    		put("bulletTankSE", null);
    		put("bulletBrickSE", null);
    		put("fireSE", null);
    		put("explosion1SE", null);
    		put("explosion2SE", null);
    		put("startStageSE", null);
    		put("pauseSE", null);
    		put("powerupAppearSE", null);
    		put("powerupPickSE", null);
    		put("gameoverSE", null);
    		put("statisticsSE", null);
    	}
    };
    
    @SuppressWarnings("serial")
	private static final Map<String, String> musicFiles = new HashMap<String, String>() {
    	{
    		put("bulletTankSE", "bullet_hit_1.wav");
    		put("bulletBrickSE", "bullet_hit_2.wav");
    		put("fireSE",  "bullet_shot.wav");
    		put("explosion1SE", "explosion_1.wav");
    		put("explosion2SE", "explosion_2.wav");
    		put("startStageSE", "stage_start.wav");
    		put("pauseSE", "pause.wav");
    		put("powerupAppearSE", "powerup_appear.wav");
    		put("powerupPickSE",  "powerup_pick.wav");
    		put("gameoverSE", "game_over.wav");
    		put("statisticsSE", "statistics_1.wav");
    	}
    };

    /**
     * Load different sound files
     */
    public static void initialize() {
        System.out.println("INITIALIZE");
//        try {
        	musicFiles.forEach((clip_name, resource_file) -> {
        		try {
        			URL urlSounds = SoundUtility.class.getClassLoader().getResource(prefix + resource_file);
        			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(urlSounds.toURI()));
        			AudioFormat format = ais.getFormat();
        			DataLine.Info info = new DataLine.Info(Clip.class, format);
        			Clip clip = (Clip)AudioSystem.getLine(info);
        			clip.open(ais);
//        			clip.setFramePosition(clip.getFrameLength());
        			clip.setFramePosition(0);
        			musicClips.put(clip_name, clip);
      	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException ex) {
                  Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
                                                                     null, ex);
      	        }	
        	});
        	
        	
        	
//            File bBse = new File("./Battle-City/sound/bullet_hit_2.wav");
////            bulletBrickSE = AudioSystem.getClip();
////            AudioInputStream ais = AudioSystem.getAudioInputStream(bBse);
////            bulletBrickSE.open(ais);
////            bulletBrickSE.setFramePosition(bulletBrickSE.getFrameLength());
//            AudioInputStream ais = AudioSystem.getAudioInputStream(bBse);
//            AudioFormat format = ais.getFormat();
//            DataLine.Info info = new DataLine.Info(Clip.class, format);
//            bulletBrickSE = (Clip)AudioSystem.getLine(info);
//            bulletBrickSE.open(ais);
//            bulletBrickSE.setFramePosition(bulletBrickSE.getFrameLength());
//            
//
//            File bTse = new File("./Battle-City/sound/bullet_hit_1.wav");
//            ais = AudioSystem.getAudioInputStream(bTse);
//            format = ais.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            bulletTankSE = (Clip)AudioSystem.getLine(info);
//            bulletTankSE.open(ais);
//            bulletTankSE.setFramePosition(bulletTankSE.getFrameLength());
//
//            
//            File fSE = new File("./Battle-City/sound/bullet_shot.wav");
//            ais = AudioSystem.getAudioInputStream(fSE);
//            format = ais.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            fireSE = (Clip)AudioSystem.getLine(info);
//            fireSE.open(ais);
//            fireSE.setFramePosition(fireSE.getFrameLength());
//
//            File ex1 = new File("./Battle-City/sound/explosion_1.wav");
//            ais = AudioSystem.getAudioInputStream(ex1);
//            format = ais.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            explosion1SE = (Clip)AudioSystem.getLine(info);
//            explosion1SE.open(ais);
//            explosion1SE.setFramePosition(explosion1SE.getFrameLength());
//
//            File ex2 = new File("./Battle-City/sound/explosion_2.wav");
//            ais = AudioSystem.getAudioInputStream(ex2);
//            format = ais.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            explosion2SE = (Clip)AudioSystem.getLine(info);
//            explosion2SE.open(ais);
//            explosion2SE.setFramePosition(explosion2SE.getFrameLength());
//
//            File stageStart = new File("./Battle-City/sound/stage_start.wav");
//            ais = AudioSystem.getAudioInputStream(stageStart);
//            format = ais.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            startStageSE = (Clip)AudioSystem.getLine(info);
//            startStageSE.open(ais);
//            startStageSE.setFramePosition(startStageSE.getFrameLength());
//
//            File pause = new File("./Battle-City/sound/pause.wav");
//            pauseSE = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(pause);
//            pauseSE.open(ais);
//            pauseSE.setFramePosition(pauseSE.getFrameLength());
//
//            File powerUpAppear = new File("./Battle-City/sound/powerup_appear.wav");
//            powerupAppearSE = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(powerUpAppear);
//            powerupAppearSE.open(ais);
//            powerupAppearSE.setFramePosition(powerupAppearSE.getFrameLength());
//
//            File powerUpPickup = new File("./Battle-City/sound/powerup_pick.wav");
//            powerupPickSE = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(powerUpPickup);
//            powerupPickSE.open(ais);
//            powerupPickSE.setFramePosition(powerupPickSE.getFrameLength());
//
//            File game_over = new File("./Battle-City/sound/game_over.wav");
//            gameoverSE = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(game_over);
//            gameoverSE.open(ais);
//            gameoverSE.setFramePosition(gameoverSE.getFrameLength());
//
//            File statistics = new File("./Battle-City/sound/statistics_1.wav");
//            statisticsSE = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(statistics);
//            statisticsSE.open(ais);
//            statisticsSE.setFramePosition(statisticsSE.getFrameLength());

//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
//            Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
//                                                               null, ex);
//        }
        initialized = true;
    }
    
    private static void playInner(String clipName) {
        if (initialized) {
        	musicClips.get(clipName).loop(1);
        } else {
            initialize();
            musicClips.get(clipName).loop(1);
        }
    }

    /**
     * Play sound for bullet hits brick
     */
    public static void BulletHitBrick() {
    	playInner("bulletBrickSE");
//        if (initialized) {
//            bulletBrickSE.loop(1);
//        } else {
//            initialize();
//            bulletBrickSE.loop(1);
//        }
    }

    /**
     * Play sound for bullet hits tank
     */
    public static void BulletHitTank() {
    	playInner("bulletTankSE");
//        if (initialized) {
//            bulletTankSE.loop(1);
//        } else {
//            initialize();
//            bulletTankSE.loop(1);
//        }
    }

    /**
     * Play sound for firing a bullet
     */
    public static void fireSound() {
    	playInner("fireSE");
//        if (initialized) {
//            fireSE.loop(1);
//        } else {
//            initialize();
//            fireSE.loop(1);
//        }
    }

    /**
     * Play sound for explosion
     */
    public static void explosion1() {
    	playInner("explosion1SE");
//        if (initialized) {
//            explosion1SE.loop(1);
//        } else {
//            initialize();
//            explosion1SE.loop(1);
//        }
    }

    /**
     * Play sound for explosion
     */
    public static void explosion2() {
    	playInner("explosion2SE");
//        if (initialized) {
//            explosion2SE.loop(1);
//        } else {
//            initialize();
//            explosion2SE.loop(1);
//        }
    }

    /**
     * Play sound for the start stage of the game
     */
    public static void startStage() {
    	playInner("startStageSE");
//        if (initialized) {
//            startStageSE.loop(1);
//        } else {
//            initialize();
//            startStageSE.loop(1);
//        }
    }

    /**
     * Play sound for the pause
     */
    public static void pause() {
    	playInner("pauseSE");
//        if (initialized) {
//            pauseSE.loop(1);
//        } else {
//            System.out.println("pause");
//            initialize();
//            pauseSE.loop(1);
//        }
    }

    /**
     * Play sound for powerUp pick
     */
    public static void powerupPick() {
    	playInner("powerupPickSE");
//        if (initialized) {
//            powerupPickSE.loop(1);
//        } else {
//            initialize();
//            powerupPickSE.loop(1);
//        }
    }

    /**
     * Play sound for gameOver
     */
    public static void gameOver() {
    	playInner("gameoverSE");
//        if (initialized) {
//            gameoverSE.loop(1);
//        } else {
//            initialize();
//            gameoverSE.loop(1);
//        }
    }

    /**
     * Play sound for the score screen
     */
    public static void statistics() {
    	playInner("statisticsSE");
//        if (initialized) {
//            statisticsSE.loop(1);
//        } else {
//            initialize();
//            statisticsSE.loop(1);
//        }
    }
}
