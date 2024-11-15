package br.com.advantageshoppingautomation.home.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import br.com.advantageshoppingautomation.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;

@Getter
public class HomePage {
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/imageViewMenu")
	private WebElement MENU_PRINCIPAL;
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/editTextSearch")
	private WebElement CAMPO_SEARCH;

	@AndroidFindBy(xpath = "//*[@resource-id='com.Advantage.aShopping:id/textViewMenuUser']")
	private WebElement BOTAO_LOGIN;
	
	public HomePage() {
		PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
	}
}
