package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GridTest2 {
	
	@Test
	void test1() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.spicejet.com/");
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']")).click();
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("DEL");
		driver.findElement(By.linkText("Delhi (DEL)")).click();
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("BOM");
		Thread.sleep(2000);
//		driver.findElement(By.linkText("Mumbai (BOM)")).click();
		WebElement DateWidget = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> columns = DateWidget.findElements(By.tagName("td"));

		for (WebElement cell: columns)
		{
			if (cell.getText().equals("30"))
			{
				cell.findElement(By.linkText("30")).click();
				break;
			}
		}
		
		driver.findElement(By.id("divpaxinfo")).click();
		Select AdultDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
		AdultDropdown.selectByValue("2");
		Select ChildrenDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Child")));
		ChildrenDropdown.selectByValue("1");
		Select InfantDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Infant")));
		InfantDropdown.selectByValue("1");
		Select CurrencyDropdown = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		CurrencyDropdown.selectByValue("INR");
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		driver.close();
	}
}
