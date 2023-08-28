package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestClass extends BaseClass {
	
	@Test(priority = 1)
	public void searchAutomation() throws IOException
	{
		ExtentTest test  = extent.createTest("Search Test", "Search a string in unibet.com");
		test.assignAuthor("Aniruddha");
		test.assignCategory("Smoke");
		test.assignDevice("Chrome");
		driver.get("https://www.unibet.co.uk/blog");
		test.log(Status.PASS, "Navigated to unibet.com");
		driver.findElement(By.name("s")).sendKeys("fifa");
		test.log(Status.PASS, "Send keys of string");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
		test.log(Status.PASS, "<b>Hit Enter after entering string</b>");

		//Test level
		test.addScreenCaptureFromPath(getScreenshot("Test level Screenshot captured"));
		//Log Level
		test.pass("Searched results are displayed succesfully", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("Searched results are displayed succesfully")).build());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 2)
	public void searchName() throws IOException, InterruptedException
	{
		ExtentTest test2 = extent.createTest("Search another Text")
				.info(MarkupHelper.createLabel("Searching a string", ExtentColor.GREEN));
		test2.assignAuthor("Aniruddha");
		test2.assignCategory("E2E");
		test2.assignDevice("Chrome");
		driver.get("https://www.unibet.co.uk/blog");
		test2.log(Status.PASS, "Navigated to unibet.com");
		driver.findElement(By.name("s")).sendKeys("Lina biswas");
		test2.log(Status.PASS, "Send keys of string");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		test2.log(Status.PASS, "<b>Hit Enter after entering string</b>");
		test2.pass("Searched results are displayed", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("Searched results are displayed")).build());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Test two Passed");	
	}


	@Test(priority = 3)
	public void loginTestCase() throws Exception {
		ExtentTest test3 = extent.createTest("Login Test");
		test3.assignAuthor("Admin");
		test3.assignCategory("Smoke");
		test3.assignDevice("Edge");
		driver.get("https://www.unibet.co.uk/");
		Thread.sleep(5000);
		//driver.findElement(By.name("username")).click();

		driver.findElement(By.xpath("//button[@class='css-h33w7b']")).click();
		Thread.sleep(5000);

	}

}
