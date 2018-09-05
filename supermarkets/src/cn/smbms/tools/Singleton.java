package cn.smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Singleton {

	private static Singleton singleton;
	private static Properties properties;

	// 私有构造器，读取数据库配置文件
	private Singleton() {
		String configFile = "database.properties";
		properties = new Properties();
		ConfigManager.class.getClassLoader();
		InputStream is = Singleton.class.getClassLoader().getSystemResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class SingletonHelper {
		private static final Singleton INSTANCE = new Singleton();
	}

	public static Singleton getInstance() {
		singleton = SingletonHelper.INSTANCE;
		return singleton;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	public static Singleton test(){
		
		return singleton;
	}

}
