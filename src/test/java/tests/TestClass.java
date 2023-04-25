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
		ExtentTest test  = extent.createTest("Search Test", "Search a string in google.com");
		test.assignAuthor("Suresh");
		test.assignCategory("Smoke");
		test.assignDevice("Chrome");
		driver.get("https://google.com");
		test.log(Status.PASS, "Navigated to Google.com");
		driver.findElement(By.name("q")).sendKeys("Automation");
		test.log(Status.PASS, "Send keys of string");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
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
		ExtentTest test2 = extent.createTest("Search another Test")
				.info(MarkupHelper.createLabel("Searching a string", ExtentColor.GREEN));
		test2.assignAuthor("Admin");
		test2.assignCategory("E2E");
		test2.assignDevice("Chrome");
		driver.get("https://google.com");
		test2.log(Status.PASS, "Navigated to Google.com");
		driver.findElement(By.name("q")).sendKeys("Suresh Anandajothi");
		test2.log(Status.PASS, "Send keys of string");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
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
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(5000);
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin123");
		test3.log(Status.PASS, "Entered Credentials successfully");
		test3.pass("Entered details successfully", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("Entered details successfully")).build());
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		test3.log(Status.PASS, "<b>Landed in to home page successfully</b>");
		Thread.sleep(5000);
		test3.pass("Login successfully", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("Login successfully")).build());
		Thread.sleep(5000);
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(5000);
		test3.pass("Logout successfully", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("Logout successfully")).build());
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

}
