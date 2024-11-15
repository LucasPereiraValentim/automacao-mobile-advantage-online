package br.com.advantageshoppingautomation.assertion;

import static br.com.advantageshoppingautomation.utils.GeneratorEvidence.closeReport;
import static br.com.advantageshoppingautomation.utils.GeneratorEvidence.createReport;
import static br.com.advantageshoppingautomation.utils.GeneratorEvidence.screenshot;
import static br.com.advantageshoppingautomation.utils.GeneratorEvidence.test;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import br.com.advantageshoppingautomation.enums.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assertion {
	
	public void validationTestByVisible(List<WebElement> elements, String testName, String description) {
		createReport(testName);
		test.log(LogStatus.INFO, description);
		for (WebElement element : elements) {
			Boolean isVisible = element.isDisplayed();
			if (isVisible) {
				log.info("Teste" + testName + Status.SUCESSO.toString());
				screenshot(Status.SUCESSO, description);
				assertTrue(isVisible);
			} else {
				log.info("Teste" + testName + Status.FALHOU.toString());
				screenshot(Status.FALHOU, description);
				assertTrue(isVisible);
			}
		}
		closeReport();
	}

	public void validationTestByText(List<WebElement> elements, String testName, String description, String text) {
		createReport(testName);
		test.log(LogStatus.INFO, description);
		for (WebElement element : elements) {
			Boolean isText = element.getText().equals(text);
			if (isText) {
				log.info("Teste" + testName + Status.SUCESSO.toString());
				screenshot(Status.SUCESSO, description);
				assertTrue(isText);
			} else {
				log.info("Teste" + testName + Status.FALHOU.toString());
				screenshot(Status.FALHOU, description);
				assertTrue(isText);
			}
		}
		closeReport();
	}
}
