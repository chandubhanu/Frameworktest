package BhanuFrameworkPractice.FrameworkTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class uploadpractice {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = System.getProperty("user.dir") + "\\Excel data\\download.xlsx";
		String Newvalue = "599";
		String fruit = "Apple";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		// Download the file
		driver.findElement(By.id("downloadButton")).click();
		// edit Excel
		int col = Updatecolumn(fileName, "Price");
		int row = updaterow(fileName, "Apple");
		update(fileName, row, col, Newvalue);
		// Upload excel
		By upload = By.cssSelector("#fileinput");
		driver.findElement(upload).sendKeys(fileName);
		// Wait for Toast message
		By Toastmessage = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Toastmessage));
		String message = driver.findElement(Toastmessage).getText();
		System.out.println(message);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Toastmessage));
		// Get the price of Apple
		String columnid = driver.findElement(By.xpath("//div[@id='cell-4-undefined']")).getAttribute("data-column-id");
		String updatedvalue = driver.findElement(By.xpath(
				"//div[text()='" + fruit + "']/parent::div/parent::div/div[@id='cell-" + columnid + "-undefined']"))
				.getText();
		System.out.println(updatedvalue);
		Assert.assertEquals(updatedvalue, Newvalue);

	}

	private static int Updatecolumn(String fileName, String colname) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		System.out.println(sheets);
		int k = 1;
		int column = 0;
		XSSFSheet sheet1 = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet1.iterator();
		Row row = rows.next();
		Iterator<Cell> ce = row.cellIterator();

		while (ce.hasNext()) {
			Cell value = ce.next();
			if (value.getStringCellValue().equalsIgnoreCase(colname))
			{
				column = k;
			}
			k++;
		}

		return column;
	}

	private static boolean update(String fileName, int row, int col, String newvalue) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowfield = sheet.getRow(row - 1);
		Cell cellfield = rowfield.getCell(col - 1);
		cellfield.setCellValue(newvalue);
		FileOutputStream output = new FileOutputStream(fileName);
		workbook.write(output);
		workbook.close();
		output.close();
		file.close();
		return true;

	}

	private static int updaterow(String fileName, String fruit) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// Identify the row
		int k = 1;
		int rowIndex = -1;
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				
				if (cell.getCellType()==CellType.STRING&&cell.getStringCellValue().equalsIgnoreCase(fruit)) {
					rowIndex = k;
				}

			}
			k++;
		}
		return rowIndex;
	}

}
