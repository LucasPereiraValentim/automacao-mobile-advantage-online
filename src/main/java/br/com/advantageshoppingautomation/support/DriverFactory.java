package br.com.advantageshoppingautomation.support;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverFactory {

	private static WebDriver instance;
	
	private final static String URL_SERVER_APPIUM = "http://127.0.0.1:4723/wd/hub";

	public static WebDriver getDriver() {
		try {
			if (instance == null) {
				if (getPlatformName().equalsIgnoreCase("android")) {
					log.info("Conectando ao device \"" + getDeviceName() + "\"");
					UiAutomator2Options uiAutomator = getOptions();
					instance = new AppiumDriver(new URL(URL_SERVER_APPIUM), uiAutomator);
				}
			} else if (getPlatformName().equalsIgnoreCase("ios")) {

			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar ao device \"" + getDeviceName() + "\"" + e.getMessage());
		}
		return instance;
	}

	private static UiAutomator2Options getOptions() {
		ReadJson readJson = new ReadJson();
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName(getPlatformName())
				.setPlatformVersion(readJson.getCapabilities().get("platformVersion").toString())
				.setUdid(getUdid())
				.setDeviceName(getDeviceName())
				.setAppPackage(readJson.getCapabilities().get("appPackage").toString())
				.setAppActivity(readJson.getCapabilities().get("appActivity").toString())
				.setAutomationName(readJson.getCapabilities().get("automationName").toString())
				.setAutoGrantPermissions(true)
				.setAppWaitDuration(Duration.ofSeconds(30));
		return options;
	}
	
	private static String getUdid() {
		return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("udid").toString();
	}
	
	public static String getPlatformName() {
		return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platformName").toString();
	}
	
	private static String getDeviceName() {
		return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName").toString();
	}
}
