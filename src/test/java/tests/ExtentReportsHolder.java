package tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportsHolder {

	ExtentReports extent;
		
	@BeforeTest
	public void SetupExtent() throws IOException {
		
		extent = new ExtentReports();
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Arch", System.getProperty("os.arch"));
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("App Url", System.getProperty("url"));
//		System.getProperties().list(System.out);
//		BaseClass base = new BaseClass();
//		extent.setSystemInfo("Browser", base.GetBrowserNameAndVersion());
//		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("STMExtentReport.html");
		
		sparkReporter.viewConfigurer().viewOrder().as(new ViewName [] {
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.AUTHOR,
					ViewName.CATEGORY,
					ViewName.DEVICE
				}).apply();
		//sparkReporter.loadJSONConfig(new File ("./src/test/resources/ExtentReport-Config.json"));
		sparkReporter.config().setReportName("Chrome Browser Appium Automation Testing");
		sparkReporter.config().setDocumentTitle("Appium Automation Testing");
		extent.attachReporter(sparkReporter);
		
		
	}
	
	@AfterTest
	public void TearDownExtent()
	{
		
		extent.flush();
	}
}
