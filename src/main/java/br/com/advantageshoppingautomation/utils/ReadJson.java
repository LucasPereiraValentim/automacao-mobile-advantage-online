package br.com.advantageshoppingautomation.utils;

import java.io.FileReader;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadJson {
	
	private JSONParser jsonParser;
	
	public ReadJson() {
		this.jsonParser = new JSONParser();
	}
	
	public JSONObject getCapabilities() {
		try {		
			if (isDeviceAndroid()) {
				return (JSONObject) jsonParser.parse(new FileReader(
						System.getProperty("user.dir") +
						"/src/main/resources/device-config/android-device-config.json"));
			
			} else if (isDeviceIOS()){
				log.info("IOS");
				return null;
			} else {
				throw new RuntimeException("Plataforma invalida!!");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao obter plataforma de execução.");
		}
	}
	
	
	private boolean isDeviceAndroid() {
		return DriverFactory.getPlatformName().equalsIgnoreCase("android");
	}
	
	private boolean isDeviceIOS() {
		return DriverFactory.getPlatformName().equalsIgnoreCase("ios");
	}
}
