package br.com.advantageshoppingautomation.home.action;

import br.com.advantageshoppingautomation.enums.Menu;
import br.com.advantageshoppingautomation.home.page.HomePage;
import br.com.advantageshoppingautomation.utils.UtilsMobile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeAction {

    private HomePage homePage;

    public HomeAction() {
        this.homePage = new HomePage();
    }

    public void acessarMenuLateral(Menu menu) {
        log.info("Clicando na aba do menu");
        UtilsMobile.click(homePage.getMENU_PRINCIPAL());
        if (menu.equals(Menu.LOGIN)) {
            UtilsMobile.click(homePage.getBOTAO_LOGIN());
        }
    }


}
