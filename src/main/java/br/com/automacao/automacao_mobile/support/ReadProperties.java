package br.com.automacao.automacao_mobile.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	private Properties prop;
	
	private FileInputStream fileInputStream;
	
	public ReadProperties() {
		try {
			this.prop = new Properties();
			this.fileInputStream = new FileInputStream(System.getProperty("user.dir") 
					+ "/src/main/resource/automation.properties");
			prop.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPlatformExecution() {
		return prop.getProperty("prop.automation.executionPlatform");
	}
	
	
}
