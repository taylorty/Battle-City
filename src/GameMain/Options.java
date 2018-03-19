package GameMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class Options {

	public enum OptionsEnum {
		start_level,
		mode,
		key_up_1,
		key_left_1,
		key_right_1,
		key_down_1,
		key_fire_1,
		key_up_2,
		key_left_2,
		key_right_2,
		key_down_2,
		key_fire_2
	}
	
	private final static String optionsResourceFolder = "options/";
	private final static String optionsFileName = "options";
	private Properties properties;
	private java.util.Map<OptionsEnum, Integer> optionMap = new HashMap<OptionsEnum, Integer>();
	
	@SuppressWarnings("serial")
	private final static java.util.Map<String, String> defaults = new HashMap<String, String>()
			{
				{
					put("start_level", "1");
					put("mode", "1");
					put("key_up_1", "38");
					put("key_left_1", "37");
					put("key_right_1", "39");
					put("key_down_1", "40");
					put("key_fire_1", "32");
					put("key_up_2", "87");
					put("key_left_2", "65");
					put("key_right_2", "68");
					put("key_down_2", "83");
					put("key_fire_2", "17");
				}
			};

	private static Options instance = new Options();
	
	private Options() {
		this.properties = new Properties();
		URL optionUrl = this.getClass().getClassLoader().getResource(optionsResourceFolder + optionsFileName);
		try {
			File optionFile = new File(optionUrl.toURI());
			if (optionFile.exists()) {
				InputStream is = new FileInputStream(optionFile);
				properties.load(is);
				is.close();
			}  
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		if (!correctProperty("start_level")) this.properties.setProperty("start_level", defaults.get("start_level"));
		if (!correctProperty("mode")) this.properties.setProperty("mode", defaults.get("mode"));

		if (!correctProperty("key_up_1")) this.properties.setProperty("key_up_1", defaults.get("key_up_1"));
		if (!correctProperty("key_left_1")) this.properties.setProperty("key_left_1", defaults.get("key_left_1"));
		if (!correctProperty("key_right_1")) this.properties.setProperty("key_right_1", defaults.get("key_right_1"));
		if (!correctProperty("key_down_1")) this.properties.setProperty("key_down_1", defaults.get("key_down_1"));
		if (!correctProperty("key_fire_1")) this.properties.setProperty("key_fire_1", defaults.get("key_fire_1"));

		if (!correctProperty("key_up_2")) this.properties.setProperty("key_up_2", defaults.get("key_up_2"));
		if (!correctProperty("key_left_2")) this.properties.setProperty("key_left_2", defaults.get("key_left_2"));
		if (!correctProperty("key_right_2")) this.properties.setProperty("key_right_2", defaults.get("key_right_2"));
		if (!correctProperty("key_down_2")) this.properties.setProperty("key_down_2", defaults.get("key_down_2"));
		if (!correctProperty("key_fire_2")) this.properties.setProperty("key_fire_2", defaults.get("key_fire_2"));
		
		optionMap.put(OptionsEnum.start_level, Integer.parseInt(this.properties.getProperty("start_level")));
		optionMap.put(OptionsEnum.mode, Integer.parseInt(this.properties.getProperty("mode")));
		optionMap.put(OptionsEnum.key_up_1, Integer.parseInt(this.properties.getProperty("key_up_1")));
		optionMap.put(OptionsEnum.key_up_2, Integer.parseInt(this.properties.getProperty("key_up_2")));
		optionMap.put(OptionsEnum.key_down_1, Integer.parseInt(this.properties.getProperty("key_down_1")));
		optionMap.put(OptionsEnum.key_down_2, Integer.parseInt(this.properties.getProperty("key_down_2")));
		optionMap.put(OptionsEnum.key_left_1, Integer.parseInt(this.properties.getProperty("key_left_1")));
		optionMap.put(OptionsEnum.key_left_2, Integer.parseInt(this.properties.getProperty("key_left_2")));
		optionMap.put(OptionsEnum.key_right_1, Integer.parseInt(this.properties.getProperty("key_right_1")));
		optionMap.put(OptionsEnum.key_right_2, Integer.parseInt(this.properties.getProperty("key_right_2")));
		optionMap.put(OptionsEnum.key_fire_1, Integer.parseInt(this.properties.getProperty("key_fire_1")));
		optionMap.put(OptionsEnum.key_fire_2, Integer.parseInt(this.properties.getProperty("key_fire_2")));
	}
	
	private boolean correctProperty(String propName) {
		String temp;
		if ((temp = this.properties.getProperty(propName)) == null) return false;
		try {
			Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static Options getInstance() {
		return instance;
	}
	
	public Integer getOption(OptionsEnum optionType) {
		return this.optionMap.get(optionType);
	}
	
	public void setOption(OptionsEnum optionType, int optionValue) {
		this.optionMap.put(optionType, optionValue);	
	}
	
	public void saveToFile() {
		properties.setProperty("start_level", Integer.toString(optionMap.get(OptionsEnum.start_level)));
		properties.setProperty("mode", Integer.toString(optionMap.get(OptionsEnum.mode)));
		properties.setProperty("key_up_1", Integer.toString(optionMap.get(OptionsEnum.key_up_1)));
		properties.setProperty("key_up_2", Integer.toString(optionMap.get(OptionsEnum.key_up_2)));
		properties.setProperty("key_down_1", Integer.toString(optionMap.get(OptionsEnum.key_down_1)));
		properties.setProperty("key_down_2", Integer.toString(optionMap.get(OptionsEnum.key_down_2)));
		properties.setProperty("key_left_1", Integer.toString(optionMap.get(OptionsEnum.key_left_1)));
		properties.setProperty("key_left_2", Integer.toString(optionMap.get(OptionsEnum.key_left_2)));
		properties.setProperty("key_right_1", Integer.toString(optionMap.get(OptionsEnum.key_right_1)));
		properties.setProperty("key_right_2", Integer.toString(optionMap.get(OptionsEnum.key_right_2)));
		properties.setProperty("key_fire_1", Integer.toString(optionMap.get(OptionsEnum.key_fire_1)));
		properties.setProperty("key_fire_2", Integer.toString(optionMap.get(OptionsEnum.key_fire_2)));
		
		URL optionUrl = this.getClass().getClassLoader().getResource(optionsResourceFolder + optionsFileName);
		try {
			File optionFile = new File(optionUrl.toURI());
			OutputStream out = new FileOutputStream(optionFile);
			properties.store(out, null);
			out.flush();
			out.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}
	
}
