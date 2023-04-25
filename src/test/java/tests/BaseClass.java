package tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass extends ExtentReportsHolder{

	WebDriver driver ;
	
	@BeforeSuite
	public void Setup() {

		try {
		//add Desired caps
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6 Pro API 30");
		//caps.setCapability("udid", "192.168.1.8:5555");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		
		// using APKInfo app in playstore to get details of below//
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("w3c", false);
		caps.merge(chromeOptions);
		
		URL url;
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, caps);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String GetBrowserNameAndVersion()
	{
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browser = cap.getBrowserName();
		String version = cap.getVersion();
		return browser + " " + version;
	}
	
	public String getScreenshot(String screenshotName)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		String path = "./src/test/resources/"+ screenshotName + ".png";
		
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return path;
	}
	
	public String getScreenshotAsBase64()
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String basePath = ts.getScreenshotAs(OutputType.BASE64);
		return basePath;
	}
	
	@Test
	public void test()
	{
		ExtentTest test2 = extent.createTest("Launch chrome Test", "Search a string in google.com");
		test2.log(Status.PASS, "Launched chrome browser succesfully");
		System.out.println("Test Completed..");
	}
	
	@AfterSuite
	public void TearDown() throws IOException {
	
		driver.quit();
		Desktop.getDesktop().browse(new File ("STMExtentReport.html").toURI());
	}
}
