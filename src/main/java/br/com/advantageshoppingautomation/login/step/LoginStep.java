package br.com.advantageshoppingautomation.login.step;

import br.com.advantageshoppingautomation.login.page.LoginPage;
import br.com.advantageshoppingautomation.utils.UtilsMobile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginStep {
	
	
	public LoginStep() {
		
	}

	@Given("que acesso o aplicativo")
	public void que_acesso_o_aplicativo() {
		LoginPage loginPage = new LoginPage();
		UtilsMobile.click(loginPage.getCategoria());
	}
	
	@And("clico no botão de login")
	public void clico_no_botao_de_login() {
		UtilsMobile.restartApp();
		LoginPage loginPage = new LoginPage();
		UtilsMobile.click(loginPage.getCategoria());
	}
}
