package br.com.advantageshoppingautomation.home.step;

import br.com.advantageshoppingautomation.enums.Menu;
import br.com.advantageshoppingautomation.home.action.HomeAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeStep {

    private HomeAction homeAction;

    public HomeStep() {
        this.homeAction = new HomeAction();
    }

    @Given("clico menu lateral")
    public void clico_no_menu_lateral() {
        this.homeAction.acessarMenuLateral(Menu.LOGIN);
    }

}
