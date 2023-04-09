package com.Framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_WAIT=10;
	
	public static String generateEmailTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "abc"+timeStamp+"@gmail.com";

	}
	
	public static Object[][] getDataFromExcel(String sheetName) {
		
		XSSFWorkbook workbook=null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\testdata\\TutorialsNinjaTestdata.xlsx")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int col=sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][col];
		
		for(int i=0; i<rows; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0; j<col; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					
				}
				
			}
		}
		return data;
	}
	 public static String captureScreenshot(WebDriver driver, String testName) {
		 File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destinationScreenshotFolder=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
			try {
				FileHandler.copy(srcScreenshot, new File(destinationScreenshotFolder));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return destinationScreenshotFolder;
	 }
}
