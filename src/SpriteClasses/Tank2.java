package SpriteClasses;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameMain.Options;
import GameMain.SoundUtility;
import GameMain.Options.OptionsEnum;
import SpriteClasses.ImageUtils.Images;

public class Tank2 extends Tank {

    
	public Tank2(int x, int y, int lives) {
		super(x, y, Images.player2Tank_up);
    	this.x = x;
    	this.y = y;
    	vis = true;
    	this.width = 32;
    	this.height = 32;
    	bullets = new ArrayList<>();
    	direction = 0;
    	this.lives = lives;
    }
	
    private void fire2() {
        Bullet aBullet;
        if (direction == 0) {
            aBullet = new Bullet(x + width / 3, y, 0, false, false);
        } else if (direction == 1) {
            aBullet = new Bullet(x + width - 3, y + height / 3, 1, false, false);
        } else if (direction == 2) {
            aBullet = new Bullet(x + width / 3, (y + height) - 3, 2, false, false);
        } else {
            aBullet = new Bullet(x, y + height / 3, 3, false, false);
        }
        if (starLevel == 3) {
            aBullet.upgrade();
        }
        bullets.add(aBullet);
        SoundUtility.fireSound();
    }
	
	@Override
    public void keyPressed(KeyEvent e) {
		if (!this.vis || !(this.health >= 0)) return;
        int time;
        int key = e.getKeyCode();
        if (starLevel == 0) {
            time = 700;
        } else {
            time = 250;
        }
        if (key == Options.getInstance().getOption(OptionsEnum.key_fire_2) && (System.currentTimeMillis() - lastFired) > time) {
            fire2();
            lastFired = System.currentTimeMillis();
        } else if (key == Options.getInstance().getOption(OptionsEnum.key_left_2)) {
            dx = -1;
            dy = 0;
            if (starLevel > 1) {
                dx = -2;
            }
            y = (int)(Math.round(((double)y / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize()); 
            updateImage(Images.player2Tank_left);
            direction = 3;
        }  else if (key == Options.getInstance().getOption(OptionsEnum.key_right_2)) { // else if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            dy = 0;
            if (starLevel > 1) {
                dx = 2;
            }
            y = (int)(Math.round(((double)y / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
            updateImage(Images.player2Tank_right);
            direction = 1;
        } else if  (key == Options.getInstance().getOption(OptionsEnum.key_up_2)) { // (key == KeyEvent.VK_UP) {
        	updateImage(Images.player2Tank_up);
            dy = -1;
            dx = 0;
            if (starLevel > 1) {
                dy = -2;
            }
            x = (int)(Math.round(((double)x / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
            direction = 0;
        } else if  (key == Options.getInstance().getOption(OptionsEnum.key_down_2)) { //(key == KeyEvent.VK_DOWN) {
        	updateImage(Images.player2Tank_down);
            dy = 1;
            dx = 0;
            if (starLevel > 1) {
                dy = 2;
            }
            x = (int)(Math.round(((double)x / ImageUtils.getDefaultBlockSize())) * ImageUtils.getDefaultBlockSize());
            direction = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == Options.getInstance().getOption(OptionsEnum.key_left_2)) {
            dx = 0;
        }

        if (key == Options.getInstance().getOption(OptionsEnum.key_right_2)) {
            dx = 0;
        }

        if (key == Options.getInstance().getOption(OptionsEnum.key_up_2)) {
            dy = 0;
        }

        if (key == Options.getInstance().getOption(OptionsEnum.key_down_2)) {
            dy = 0;
        }
    }

	
}
