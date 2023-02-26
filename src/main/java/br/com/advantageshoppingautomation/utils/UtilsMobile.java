package br.com.advantageshoppingautomation.utils;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.advantageshoppingautomation.support.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilsMobile {
	
	public static AppiumDriver getDriver() {
		return (AppiumDriver) DriverFactory.getDriver();
	}
	
	public static void scrollLeft(WebElement elementInit, WebElement elementMoveTo) {
		waitElementToBeClickable(elementInit);
		int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
		double startX = elementInit.getRect().x + (elementInit.getSize().width * 0.9);
		double endX = elementInit.getRect().x + (elementInit.getSize().width * 0.1);
		scroll(centerY, startX, endX, elementMoveTo);
	}
	public static void scrollRight(WebElement elementInit, WebElement elementMoveTo) {
		waitElementToBeClickable(elementInit);
		int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
		double startX = elementInit.getRect().x + (elementInit.getSize().width * 0.1);
		double endX = elementInit.getRect().x + (elementInit.getSize().width * 0.9);
		scroll(centerY, startX, endX, elementMoveTo);
	}
	
	
	public static void scrollDown(WebElement elementInit, WebElement elementMoveTo) {
		waitElementToBeClickable(elementInit);
		int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);
		double startY = elementInit.getRect().y + (elementInit.getSize().height * 0.9);
		double endY = elementInit.getRect().y + (elementInit.getSize().height * 0.1);
		scroll(centerX, startY, endY, elementMoveTo);
	}
	
	public static void scrollTop(WebElement elementInit, WebElement elementMoveTo) {
		waitElementToBeClickable(elementInit);
		int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);
		double startY = elementInit.getRect().y + (elementInit.getSize().height * 0.1);
		double endY = elementInit.getRect().y + (elementInit.getSize().height * 0.9);
		scroll(centerX, startY, endY, elementMoveTo);
	}

	private static void scroll(int centerX, double startY, double endY, WebElement elementMoveTo) {
		for (int i = 1; i < 20; i++) {
			
			if (isVisible(elementMoveTo)) {
				log.info("Elemento encontrado");
				break;
			}
			log.info("Procurando elemento. Tentativa N°" + i);
			log.info("Realizando Scroll...");
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			
			Sequence swipe = new Sequence(finger, 1);
			
			swipe.addAction(
					finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), centerX, (int) startY));
			
			swipe.addAction(finger.createPointerDown(0));
			
			swipe.addAction(
					finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), centerX, (int) endY));
			
			swipe.addAction(finger.createPointerUp(0));
			
			getDriver().perform(Arrays.asList(swipe));
		}		
	}
	
	private static boolean isVisible(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
			StartsActivity startsActivity = (AndroidDriver) getDriver();
			startsActivity.startActivity(new Activity(null, null));
		}
	}
}
