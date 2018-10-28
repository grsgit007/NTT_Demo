package googleDemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public Config(String configPath) {
		try {
			FileInputStream fis = new FileInputStream(configPath);
			properties = new Properties();
			properties.load(fis);
			fis.close();
		} catch (IOException ex) {

		}
	}

	public String getPropValue(String key) throws Exception {
		if (!properties.containsKey(key)) {
			throw new Exception("Invalid Key " + key);
		}
		return properties.getProperty(key);
	}
}
