package br.com.advantageshoppingautomation.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import br.com.advantageshoppingautomation.enums.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorEvidence {

	private static ExtentReports reports;

	public static ExtentTest test;

	public static void createReport(String scenario) {
		String dataEvidence = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
		File file = new File(System.getProperty("user.dir") + "\\evidences\\" + dataEvidence);
		file.mkdirs();
		File report = new File(
				file.getAbsolutePath() + File.separator + scenario.replace(" ", "_").toLowerCase() + ".html");

		reports = new ExtentReports(report.toString());
		test = reports.startTest("ExtentDemo");

	}

	public static void closeReport() {
		reports.endTest(test);
		reports.flush();
	}

	public static void screenshot(Status status, String descricao) {
		try {

			String screenshot = ((TakesScreenshot) UtilsMobile.getDriver()).getScreenshotAs(OutputType.BASE64);
			
			switch (status) {
				case SUCESSO:
					test.log(LogStatus.PASS, test.addBase64ScreenShot(screenshot) + descricao);
					break;
				case FALHOU:
					test.log(LogStatus.FAIL, test.addBase64ScreenShot(screenshot) + descricao);
					break;
				case INFO:
					test.log(LogStatus.INFO, test.addBase64ScreenShot(screenshot) + descricao);
					break;
			}
		} catch (Exception e) {
			log.info("Erro ao gerar screenshot. Exception: {}", e.getMessage());
		}
	}
}
