package br.com.automacao.automacao_mobile.support;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.automacao.automacao_mobile.support.model.DeviceConfig;

public class ReadJson {
		
	private ReadProperties readProperties;
	
	private ObjectMapper objectMapper;
	
	private DeviceConfig deviceConfig;
	
	public ReadJson() {
		this.readProperties = new ReadProperties();
		this.objectMapper 	= new ObjectMapper();
	}
	
	public DeviceConfig getCapabilities() {
		this.readProperties = new ReadProperties();
		try {		
			if (isDeviceAndroid()) {
				this.deviceConfig = this.objectMapper.readValue(
						new File(System.getProperty("user.dir") +
								"/src/main/resource/device-config/android-device-config.json"), DeviceConfig.class);
				return this.deviceConfig;
			} else if (isDeviceIOS()){
				return null;
			} else {
				throw new Exception("Plataforma invalida!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private boolean isDeviceAndroid() {
		return this.readProperties.getPlatformExecution().equalsIgnoreCase("android");
	}
	
	private boolean isDeviceIOS() {
		return this.readProperties.getPlatformExecution().equalsIgnoreCase("ios");
	}
}
