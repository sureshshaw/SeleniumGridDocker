package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridTest {

	private static ThreadLocal<RemoteWebDriver> threadRWD = new ThreadLocal<RemoteWebDriver>();

	@Parameters("browser")
	@Test
	void test1(String browser) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(browser);
		URL url = null;
		try {
			url = new URL("http://localhost:4444/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		RemoteWebDriver remoteDriver = new RemoteWebDriver(url, cap);
		threadRWD.set(remoteDriver);
		threadRWD.get().get("http://www.spicejet.com/");
		threadRWD.get().manage().window().maximize();
		threadRWD.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		threadRWD.get().findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']")).click();
		threadRWD.get().findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("DEL");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		threadRWD.get().findElement(By.linkText("Delhi (DEL)")).click();
		threadRWD.get().findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("BOM");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		threadRWD.get().findElement(By.linkText("Mumbai (BOM)")).click();
		WebElement DateWidget = threadRWD.get().findElement(By.id("ui-datepicker-div"));
		List<WebElement> columns = DateWidget.findElements(By.tagName("td"));

		for (WebElement cell: columns)
		{
			if (cell.getText().equals("30"))
			{
				cell.findElement(By.linkText("30")).click();
				break;
			}
		}
		threadRWD.get().findElement(By.id("divpaxinfo")).click();
		Select AdultDropdown = new Select(threadRWD.get().findElement(By.id("ctl00_mainContent_ddl_Adult")));
		AdultDropdown.selectByValue("2");
		Select ChildrenDropdown = new Select(threadRWD.get().findElement(By.id("ctl00_mainContent_ddl_Child")));
		ChildrenDropdown.selectByValue("1");
		Select InfantDropdown = new Select(threadRWD.get().findElement(By.id("ctl00_mainContent_ddl_Infant")));
		InfantDropdown.selectByValue("1");
		Select CurrencyDropdown = new Select(threadRWD.get().findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		CurrencyDropdown.selectByValue("INR");
		threadRWD.get().findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		threadRWD.get().close();
		threadRWD.remove();
	}
}
