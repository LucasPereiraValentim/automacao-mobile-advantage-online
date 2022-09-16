package br.com.automacao.automacao_mobile.support;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getInstanceAppium() {
		if (driver == null) {
			ReadJson readJson = new ReadJson();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium:platformName", readJson.getCapabilities().getPlatformName());
			capabilities.setCapability("appium:deviceName", readJson.getCapabilities().getDeviceName());
			capabilities.setCapability("appium:appPackage", readJson.getCapabilities().getAppPackage());
			capabilities.setCapability("appium:appActivity", readJson.getCapabilities().getAppActivity());
			capabilities.setCapability("appium:udid", readJson.getCapabilities().getUdid());
			capabilities.setCapability("appium:automationName", readJson.getCapabilities().getAutomationName());
			capabilities.setCapability("appium:noReset", true);
			capabilities.setCapability("appium:unicodeKeyboard", "true");
			capabilities.setCapability("aapium:appWaitDuration",
										readJson.getCapabilities().getAppWaitDuration());
			try {
				log.info("Conectando ao device \"" + readJson.getCapabilities().getDeviceName() + "\"");
				driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				return driver;
			} catch (Exception e) {
				log.error("Erro ao conectar ao device");
				return null;
			}	
		}
		return driver;
	}
}
