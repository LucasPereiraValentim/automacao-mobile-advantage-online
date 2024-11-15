package br.com.advantageshoppingautomation.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilsMobile {
	
	public static AppiumDriver getDriver() {
		return DriverFactory.getDriver();
	}
	

	
	public static void waitElementToVisibility(WebElement element) {
		log.info("Aguardando elemento ficar visivel...");
		new WebDriverWait(getDriver(), Duration.ofSeconds(40))
		.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitElementToBeClickable(WebElement element) {
		log.info("Aguardando elemento ficar disponível para clique...");
		new WebDriverWait(getDriver(), Duration.ofSeconds(40))
		.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void backPage() {
		log.info("Retornando página");
		getDriver().navigate().back();
	}
	
	public static void click(WebElement element) {
		waitElementToBeClickable(element);
		element.click();
	}
	
	public static void clearInput(WebElement element) {
		waitElementToBeClickable(element);
		element.clear();
	}
	
	public static void sendKeys(WebElement element, String texto) {
		waitElementToBeClickable(element);
		element.sendKeys(texto);
	}
	
	public static void restartApp() {
		if (getDriver().getCapabilities().getCapability("platformName")
				.toString().equalsIgnoreCase("android")) {
			String packageApp = getDriver().getCapabilities().getCapability("appPackage").toString();
			((AndroidDriver) getDriver()).terminateApp(packageApp);
			((AndroidDriver) getDriver()).activateApp(packageApp);
		}
	}
}
