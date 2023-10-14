package br.com.advantageshoppingautomation.menu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import br.com.advantageshoppingautomation.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;

@Getter
public class MenuPage {
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/textViewMenuUser")
	private WebElement MENU_LOGIN;
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.Advantage.aShopping:id/textViewMenuHome']")
	private WebElement MENU_HOME;
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/textViewMenuSettings")
	private WebElement MENU_SETTINGS;
	
	public MenuPage() {
		PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
	}
}
