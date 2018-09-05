package cn.smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties properties;
	
	//它必须自行创建这个实例（定义了静态的该类私有对象）
	private static ConfigManager configManager = new ConfigManager();
	
	//私有构造器，读取数据库配置文件
	private ConfigManager() {
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader().getSystemResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//全局访问点（提供一个静态的公有方法，返回创建或者获取本身的静态私有对象）
	public static synchronized ConfigManager getInstance(){
		//if(configManager == null){
		//	configManager = new ConfigManager();
		//}
		return configManager;
	}
	
	public String getValue(String key){
		return properties.getProperty(key);
	}

}
