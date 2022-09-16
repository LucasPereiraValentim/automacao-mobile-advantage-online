package br.com.automacao.automacao_mobile.utils;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.automacao.automacao_mobile.support.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilsMobile {
	
	public static AppiumDriver getDriver() {
		return (AppiumDriver) DriverFactory.getInstanceAppium();
	}
	
	public static void scrollDown(WebElement elementInit, WebElement elementMoveTo) {

		int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);

		double startY = elementInit.getRect().y + (elementInit.getSize().height * 0.9);

		double endY = elementInit.getRect().y + (elementInit.getSize().height * 0.1);
		scroll(centerX, startY, endY, elementMoveTo);
	}
	
	public static void scrollTop(WebElement elementInit, WebElement elementMoveTo) {

		int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);

		double startY = elementInit.getRect().y + (elementInit.getSize().height * 0.1);
		
		double endY = elementInit.getRect().y + (elementInit.getSize().height * 0.9);
		scroll(centerX, startY, endY, elementMoveTo);
	}

	public static void scroll(int centerX, double startY, double endY, WebElement elementMoveTo) {
		for (int i = 0; i < 20; i++) {
			if (isVisible(elementMoveTo)) {
				log.info("Elemento encontrado");
				break;
			}
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
	
	public static boolean isVisible(WebElement element) {
		try {
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public static void waitElementToVisibility(WebElement element, Duration duration) {
		log.info("Aguardando elemento ficar visivel...");
		new WebDriverWait(DriverFactory.getInstanceAppium(), duration)
		.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitElementToBeClickable(WebElement element, Duration duration) {
		log.info("Aguardando elemento ficar disponível para clique...");
		new WebDriverWait(DriverFactory.getInstanceAppium(), duration)
		.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void backPage() {
		log.info("Retornando página");
		getDriver().navigate().back();
	}
	
	public static void click(WebElement element) {
		element.click();
	}
	
	public static void clearInput(WebElement element) {
		element.clear();
	}
	
	public static void writeField(WebElement element, String texto) {
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
