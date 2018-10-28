package googleDemo.tests;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import googleDemo.pages.HomePage;
import googleDemo.utils.CommonUtils;
import googleDemo.utils.ExtentManager;
import googleDemo.utils.Setup;

public class KeywordSearchTest {

	WebDriver driver;
	HomePage home;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void extentSetup() {
		extent = ExtentManager.getExtent();
	}

	@BeforeMethod
	public void setup() throws FileNotFoundException {
		// test=extent.createTest(this.getClass().getSimpleName());
		driver = CommonUtils.initialize(driver, Setup.url, Setup.browser);
		home = PageFactory.initElements(driver, HomePage.class);

	}

	@Test
	public void itemSearch() throws InterruptedException {
		test = extent.createTest(this.getClass().getSimpleName());
		home.searchKeyword("Life in India");
		Assert.assertTrue(driver.getTitle().equals("Life in India - Google Search"),
				"Unable to get the search result.");
		test.log(Status.INFO, "Searched Keyword Successfully.");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			CommonUtils.getScreenshotCapture(driver, Setup.screenshotfolder, getClass().getSimpleName());
			test.log(Status.FAIL, "Test Case Failed: " + getClass().getSimpleName());
		} else {
			test.log(Status.PASS, "Test Case Passed: " + getClass().getSimpleName());
		}
		driver.close();
	}

	@AfterSuite
	public void extentClose() {
		extent.flush();
	}

}
