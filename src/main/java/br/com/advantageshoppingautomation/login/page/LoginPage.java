package br.com.advantageshoppingautomation.login.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import br.com.advantageshoppingautomation.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;

@Getter
public class LoginPage {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='MICE']")
	private WebElement categoria;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LAPTOPS']")
	private WebElement Laptops;
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/recyclerViewCategories")
	private WebElement telaPadrao;
	
	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
	}
}
