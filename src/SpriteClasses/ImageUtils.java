package SpriteClasses;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	public static enum Images{
		playerTank_up("playerTank_up.png", true),
		playerTank_down("playerTank_down.png", true),
		playerTank_right("playerTank_right.png", true),
		playerTank_left("playerTank_left.png", true),

		player2Tank_up("player2Tank_up.png", true),
		player2Tank_down("player2Tank_down.png", true),
		player2Tank_right("player2Tank_right.png", true),
		player2Tank_left("player2Tank_left.png", true),
		
		aiTank_basic_up("tank_basic_up.png", true),
		aiTank_basic_down("tank_basic_down.png", true),
		aiTank_basic_right("tank_basic_right.png", true),
		aiTank_basic_left("tank_basic_left.png", true),

		aiTank_armor_up("tank_armor_up.png", true),
		aiTank_armor_down("tank_armor_down.png", true),
		aiTank_armor_right("tank_armor_right.png", true),
		aiTank_armor_left("tank_armor_left.png", true),

		aiTank_power_up("tank_power_up.png", true),
		aiTank_power_down("tank_power_down.png", true),
		aiTank_power_right("tank_power_right.png", true),
		aiTank_power_left("tank_power_left.png", true),

		aiTank_fast_up("tank_fast_up.png", true),
		aiTank_fast_down("tank_fast_down.png", true),
		aiTank_fast_right("tank_fast_right.png", true),
		aiTank_fast_left("tank_fast_left.png", true),
		
		steel("wall_steel.png"),
		river("water_1.png"),
		river2("water_2.png"),
		
		base("base.png", true),
		base2("base_destroyed.png", true),
		
		tree("trees.png"),
		
		brick("wall_brick.png"),
		
		edge("edge.png"),
		
		explosion("bullet_explosion_1.png", true),
		explosion2("bullet_explosion_2.png", true),
		explosion3("bullet_explosion_3.png", true),

		shield("shield_1.png", true),
		shield2("shield_2.png", true),

		appear("appear_1.png", true),
		appear2("appear_2.png", true),
		appear3("appear_3.png", true),
		appear4("appear_4.png", true),
		
		big_explosion("big_explosion_1.png", true, true),
		big_explosion2("big_explosion_2.png", true, true),
		big_explosion3("big_explosion_3.png", true, true),
		big_explosion4("big_explosion_4.png", true, true),
		big_explosion5("big_explosion_5.png", true, true),
		
		bullet_up("bullet_up.png"),
		bullet_down("bullet_down.png"),
		bullet_right("bullet_right.png"),
		bullet_left("bullet_left.png"),
		
		powerup_timer("powerup_timer.png", true),
		
		powerup_bomb("powerup_grenade.png", true),
		
		powerup_helmet("powerup_helmet.png", true),
		
		powerup_shovel("powerup_shovel.png", true),
		
		powerup_star("powerup_star.png", true),
		
		powerup_tank("powerup_tank.png", true),
		
		lives("lives.png", true),
		flagIcon("flag.png", true),
		enemyIcon("enemy.png"),
		arrow("arrow.png"),
		background("battle_city.png", true, true),
		tree2("trees2.png", true, true)
		
//        tankBasic = loadImage("./Battle-City/image/tank_basic.png");
//        tankFast = loadImage("./Battle-City/image/tank_fast.png");
//        tankPower = loadImage("./Battle-City/image/tank_power.png");
//        tankArmor = loadImage("./Battle-City/image/tank_armor.png");
//        tank = loadImage("./Battle-City/image/playerTank_right.png");
		
		;
		
		private String fileName;
		private boolean big;
		private boolean extraLarge;
		
		private Images(String fileName) {
			this.fileName = fileName;
			this.big = false;
			this.extraLarge = false;
		}

		private Images(String fileName, boolean big) {
			this.fileName = fileName;
			this.big = true;
			this.extraLarge = false;
		}

		private Images(String fileName, boolean big, boolean large) {
			this.fileName = fileName;
			this.big = true;
			this.extraLarge = true;
		}

		
		public String getFileName() {
			return fileName;
		}

		public boolean isBig() {
			return big;
		}

		public boolean isExtraLarge() {
			return extraLarge;
		}
		
	}
	
	private static final String prefix = "images/";
	private static Map<Images, Image[]> images = null;
	private static Map<Images, Image> originImages = null;
	private static double[] scales;
	private static int[] block_sizes;
	private static final int default_block_size = 16;
	private static final int default_big_block_size = 32;
	private static final int default_large_block_size = 64;
	private static int current_scale = 0;
	private static double current_scale_factor = 1.0;
	private static int current_block_size = default_block_size;
	private static int current_board_height = default_block_size * 28;
	private static int current_board_width = default_block_size * 33;
	private static int draw_from_x = 0;
	private static int draw_from_y = 0;
	private static int current_form_width = 528;
	
	
	public static void initialize(Integer sizes) throws IOException, URISyntaxException {
		if (images == null) {
			if (sizes == null || sizes <= 0) {
				sizes = 4;
				GraphicsEnvironment ge      = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[]    gs      = ge.getScreenDevices();
				OptionalInt maxBlockSize = Arrays.stream(gs).mapToInt(gs_ -> {
					if (gs_.getDisplayMode().getWidth() / 33 < gs_.getDisplayMode().getHeight() / 28) return gs_.getDisplayMode().getWidth() / 33; 
					return gs_.getDisplayMode().getHeight() / 28;
				}).max();
				if (maxBlockSize.isPresent()) sizes = (4 * maxBlockSize.getAsInt() / default_block_size) + 2; 
				sizes = sizes > 4 ? sizes : 4;
			}
			images = new HashMap<>();
			originImages = new HashMap<>();
			scales = DoubleStream.iterate(1.0, d -> {return d + .25;}).limit(sizes).toArray();
			block_sizes = Arrays.stream(scales).mapToInt(f -> {return (int)(f * default_block_size);}).toArray();
			for (Images im : Images.values()) {
    			URL imageUrl = ImageUtils.class.getClassLoader().getResource(prefix + im.getFileName());
    			BufferedImage source = ImageIO.read(new File(imageUrl.toURI()));
    			originImages.put(im, source);
    			List<Image> images_ = new ArrayList<>(sizes);  
    			DoubleStream.iterate(1.0, d -> {return d + .25;}).limit(sizes).forEach(f -> {
    				int dWidth = (int)(f * default_block_size), dHeight = (int)(f * default_block_size);
    				if (im.isExtraLarge()) {
    					dWidth = (int)(f * default_large_block_size);
    					dHeight = (int)(f * default_large_block_size);
    				} else if (im.isBig()) {
    					dWidth = (int)(f * default_big_block_size);
    					dHeight = (int)(f * default_big_block_size);
    				}
    				BufferedImage result = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_4BYTE_ABGR);
			        Graphics2D g = result.createGraphics();
			        AffineTransform at = AffineTransform.getScaleInstance(f, f);
			        g.drawRenderedImage(source, at);
			        images_.add(result);
    			});
    			images.put(im, images_.toArray(new Image[images_.size()]));
			}
		}
	}
	
	public static void setNewFormSize(int newWidth, int newHeight) {
		int size_block = (int)((((double)newWidth) / 33));
		int size_block_ = (int)((((double)newHeight) / 28));
		size_block = size_block_ < size_block ?  size_block_ : size_block;
		int newSize = 0;
		for (int i = 0; i < block_sizes.length; i++) 
			if (block_sizes[i] > size_block) {
				newSize = i == 0 ? 0 : i - 1;
				break;
		}
		current_scale_factor = scales[newSize];
		current_block_size = (int)(scales[newSize] * default_block_size); 
		current_scale = newSize;
		current_board_height = current_block_size * 28; 
		current_board_width = current_block_size * 33;
		draw_from_x = (newWidth / 2) - (int)(16.5 * current_block_size);
		draw_from_y = (newHeight / 2) - (14 * current_block_size);
		current_form_width = newWidth;
	}

	public static int getCurrent_scale() {
		return current_scale;
	}

	public static int getCurrent_block_size() {
		return current_block_size;
	}
	
	public static Image getImage(Images imageType) {
		if (imageType == null) return null;
		return images.get(imageType)[current_scale];
	}
	
	public static Image getOriginImage(Images imageType) {
		if (imageType == null) return null;
		return originImages.get(imageType);
	}
	
	public static int getCurrent_board_height() {
		return current_board_height;
	}
	
	public static int getCurrent_board_width() {
		return current_board_width;
	}

	public static double getCurrent_scale_factor() {
		return current_scale_factor;
	}

	public static int getDefaultBlockSize() {
		return default_block_size;
	}

	public static int getDraw_from_x() {
		return draw_from_x;
	}

	public static int getDraw_from_y() {
		return draw_from_y;
	}

	public static int getCurrent_form_width() {
		return current_form_width;
	}
	
	
	
	
	
}
