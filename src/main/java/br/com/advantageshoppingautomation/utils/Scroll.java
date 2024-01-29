package br.com.advantageshoppingautomation.utils;

import br.com.advantageshoppingautomation.enums.ScrollDirection;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class Scroll {

    public static void scroll(ScrollDirection scrollDirection, WebElement elementInit, WebElement elementMoveTo) {
        switch (scrollDirection) {
            case TOP:
                scrollTop(elementInit, elementMoveTo);
                break;
            case DOWN:
                scrollDown(elementInit, elementMoveTo);
                break;
            case LEFT:
                scrollLeft(elementInit, elementMoveTo);
                break;
            case RIGHT:
                scrollRight(elementInit, elementMoveTo);
                break;
            default:
                throw new RuntimeException("Direção de scroll incorreta...");
        }
    }

    private static void scrollLeft(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit);
        int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
        double startX = elementInit.getRect().x + (elementInit.getSize().width * 0.9);
        double endX = elementInit.getRect().x + (elementInit.getSize().width * 0.1);
        scroll(centerY, startX, endX, elementMoveTo);
    }
    private static void scrollRight(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit);
        int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
        double startX = elementInit.getRect().x + (elementInit.getSize().width * 0.1);
        double endX = elementInit.getRect().x + (elementInit.getSize().width * 0.9);
        scroll(centerY, startX, endX, elementMoveTo);
    }


    private static void scrollDown(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit);
        int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);
        double startY = elementInit.getRect().y + (elementInit.getSize().height * 0.9);
        double endY = elementInit.getRect().y + (elementInit.getSize().height * 0.1);
        scroll(centerX, startY, endY, elementMoveTo);
    }

    private static void scrollTop(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit);
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

            UtilsMobile.getDriver().perform(Arrays.asList(swipe));
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


}
