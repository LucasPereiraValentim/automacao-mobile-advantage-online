package br.com.automacao.automacao_mobile.support;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverFactory {
	
	
	
	public static AppiumDriver getAppiumDriver() {
		UiAutomator2Options options = new UiAutomator2Options()
	               .setPlatformName("Android")
	               .setDeviceName("deviceName").setAppPackage("appPackage")
	               .setAppActivity("appActivity")
	               .setUnlockKey("unlockKey")
	               .setNoReset(true)
	               .eventTimings();
		
		return null;
	}
}
