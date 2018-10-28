package googleDemo.utils;

import java.util.HashMap;

public class Setup {

	public static Config config = new Config("src/main/resources/env.properties");

	public static String url;
	public static String browser;
	public static int MinWait;
	public static int MaxWait;
	public static String ChromeDriverPath;
	public static String screenshotfolder;
	public static String extentPath;

	static {

		try {
			url = config.getPropValue("URL");
			browser = config.getPropValue("browser");
			MinWait = Integer.valueOf(config.getPropValue("minWait"));
			MaxWait = Integer.valueOf(config.getPropValue("maxWait"));
			ChromeDriverPath = config.getPropValue("CHROMEDRIVERPATH");
			screenshotfolder= config.getPropValue("screenshotfolder");
			extentPath= config.getPropValue("ExtentPath");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static HashMap<String, String> environment(String envName) {

		HashMap<String, String> env = new HashMap<String, String>();

		if ("TEST".equalsIgnoreCase(envName)) {
			env.put("URL", "http://google.co.uk");
			env.put("dbserver", "DBServerName");
			env.put("dbinstance", "DBServerName");
		} else if ("PROD".equalsIgnoreCase(envName)) {
			env.put("URL", "http://google.com");
			env.put("dbserver", "LONMS12358");
			env.put("dbinstance", "UMGBSIT04");
		}
		return env;
	}

}
