package br.com.automacao.automacao_mobile.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorEvidence { 
	
	private static Document doc;

	public static void createPDF(String scenario) {
		String dataEvidence = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
		File file = new File(System.getProperty("user.dir") + "\\evidences\\" + dataEvidence);
		file.mkdirs();
		File filePdf = new File(file.getAbsolutePath() + File.separator + scenario.replace(" ", "_").toLowerCase() + ".pdf");
		doc = new Document(PageSize.A4);
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(filePdf);
			PdfWriter.getInstance(doc, outStream);
			doc.open();
			addInfoTeste(doc, scenario);
		} catch (FileNotFoundException | DocumentException e) {
			log.error("Erro ao criar PDF. Exception: {}", e.getMessage());
			filePdf.delete();
		}
	}

	private static void addInfoTeste(Document doc, String scenario) throws DocumentException {

		String initTestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

		Font fontCabecalho = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

		PdfPTable tableHeader = new PdfPTable(new float[] { 0.15f, 0.35f, 0.13f, 0.37f});

		PdfPCell header = new PdfPCell(new Paragraph("Informações do teste", fontCabecalho));

		header.setUseBorderPadding(true);
		header.setBorderColor(BaseColor.BLACK);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);

		header.setColspan(4);
		tableHeader.setSpacingBefore(20);

		PdfPCell infoProject = new PdfPCell(new Paragraph("Projeto", fontCabecalho));
		PdfPCell nameProject = new PdfPCell(new Paragraph("Advantage Online Shopping", fontCabecalho));
		PdfPCell infoDateTime = new PdfPCell(new Paragraph("Início da execução", fontCabecalho));
		PdfPCell dateTime = new PdfPCell(new Paragraph(initTestTime, fontCabecalho));
		PdfPCell infoScenario = new PdfPCell(new Paragraph("Cenário", fontCabecalho));
		PdfPCell nameScenario = new PdfPCell(new Paragraph(scenario, fontCabecalho));
		tableHeader.addCell(infoProject);
		tableHeader.addCell(nameProject);
		tableHeader.addCell(infoDateTime);
		tableHeader.addCell(dateTime);
		tableHeader.addCell(infoScenario);
		tableHeader.addCell(nameScenario);
		doc.add(tableHeader);
	}

	public static void screenshot(String description) {
		try {
			
			byte[] screenshot = ((TakesScreenshot) UtilsMobile.getDriver()).getScreenshotAs(OutputType.BYTES);

			Image imagePage = Image.getInstance(screenshot);
			
			imagePage.scaleAbsolute(500f, 250f);
	
			doc.add(imagePage);
			
			setDescriptionScreenshot(description);
			
		} catch (Exception e) {
			log.info("Erro ao gerar screenshot. Exception: {}", e.getMessage());
		}
		
	}
	
	
	private static void setDescriptionScreenshot(String description) throws DocumentException {
		Paragraph descriptionPDF = new Paragraph(description);
		descriptionPDF.setAlignment(1);;
		doc.add(descriptionPDF);
	}
	
	public static void setResultTestPDF(Boolean result) {
		String finalTestTime = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(new float[] { 0.15f, 0.35f, 0.13f, 0.37f});

		PdfPCell header = new PdfPCell(new Paragraph("Resultado do teste", font));

		header.setUseBorderPadding(true);
		header.setBorderColor(BaseColor.BLACK);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);

		header.setColspan(4);
		table.setSpacingBefore(20);

		PdfPCell infoDateTime = new PdfPCell(new Paragraph("Fim da execução", font));
		PdfPCell dateTime = new PdfPCell(new Paragraph(finalTestTime, font));
		PdfPCell infoResult = new PdfPCell(new Paragraph("Resultado", font));
		PdfPCell resultTest = null;
		
		table.addCell(infoDateTime);
		table.addCell(dateTime);
		table.addCell(dateTime);
		table.addCell(infoResult);

		if (result) {
			resultTest = new PdfPCell(new Paragraph("TEST PASSED", font));
			table.addCell(resultTest).setBackgroundColor(BaseColor.GREEN);
		} else {
			resultTest = new PdfPCell(new Paragraph("TEST FAILED", font));
			table.addCell(resultTest).setBackgroundColor(BaseColor.RED);
		}
		try {
			doc.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closePDF() {
		doc.close();
	}
}
