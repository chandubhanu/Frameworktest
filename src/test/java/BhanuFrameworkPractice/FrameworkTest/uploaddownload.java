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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class uploaddownload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		String fruitname = "Apple";
		String Updatedvalue="599";
		String fileName = System.getProperty("user.dir") + "\\Excel data\\download.xlsx";
		WebDriver driver = new ChromeDriver();
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
//download
		driver.findElement(By.cssSelector("#downloadButton")).click();
		//editexcel-getColumnNumber of Price
				int col = getColumnNumber(fileName, "Price");
				int row = getRowNumber(fileName, "Apple");
				Assert.assertTrue(updatecell(fileName, row, col, Updatedvalue));
//upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys(fileName);


//wait for success message
		By toastmessage = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(toastmessage));
		String message = driver.findElement(toastmessage).getText();
		System.out.println(message);
		// Assert.assertEquals(message, "Updated Excel Data Successfully.");
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(toastmessage));

//verify the updated data is shown or not
		String pricecolumn = driver.findElement(By.xpath("//div[@id='cell-4-undefined']"))
				.getAttribute("data-column-id");
		String actualprice = driver.findElement(By.xpath("//div[text()='" + fruitname
				+ "']/parent::div/parent::div/div[@id='cell-" + pricecolumn + "-undefined']")).getText();
		System.out.println("actualprice");
		Assert.assertEquals(actualprice, Updatedvalue);
	}

	private static boolean updatecell(String fileName, int row, int col, String Updatedvalue) throws IOException {
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowfield = sheet.getRow(row - 1);
		Cell cellfield = rowfield.getCell(col - 1);
		cellfield.setCellValue(Updatedvalue);
		FileOutputStream output = new FileOutputStream(fileName);
		workbook.write(output);
		workbook.close();
		output.close();
		file.close();
		return true;
	}

	private static int getColumnNumber(String fileName, String colName) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// Identify the row
		Iterator<Row> rows = sheet.iterator();
		Row firstrow = rows.next();
		Iterator<Cell> ce = firstrow.cellIterator();
		int column = 0;
		int k = 1;
		while (ce.hasNext()) {
			Cell value = ce.next();
			
			if (value.getStringCellValue().equalsIgnoreCase(colName)) {
				column = k;
			}
			k++;
		}
		System.out.println(column);
		return column;
	}

	private static int getRowNumber(String fileName, String textname) throws IOException {
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
				
				if (cell.getCellType()==CellType.STRING&&cell.getStringCellValue().equalsIgnoreCase(textname)) {
					rowIndex = k;
				}

			}
			k++;
		}
		return rowIndex;
	}

}
