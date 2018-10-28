package googleDemo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class CommonUtils {

	public static WebDriver initialize(WebDriver driver, String url, String browser) throws FileNotFoundException {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
					+ Setup.ChromeDriverPath);
			driver = new ChromeDriver();
		} 
		else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
					+ Setup.ChromeDriverPath);
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		driver.manage().window().maximize();
		return driver;

	}

	public static void hoverElement(WebDriver driver, WebElement el) {
		Actions a = new Actions(driver);
		a.moveToElement(el).perform();

	}

	public static void waitForWebElementtoShowup(WebDriver driver, WebElement we, int maximumtimeinseconds)
			throws InterruptedException {

		try {
			if (maximumtimeinseconds > 0) {
				we.isDisplayed();

			} else {
				System.err.println("Element not found: " +we);
			}
		} catch (Exception e) {
			Thread.sleep(1000);

			waitForWebElementtoShowup(driver, we, maximumtimeinseconds - 1);
		}
		Thread.sleep(1000);
	}

	public static String getScreenshotCapture(WebDriver driver, String srcPath, String testCaseName) {
		File screencapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String scrpath = DateFormat.getDateTimeInstance().format(new Date()).replace(":", "_");
		scrpath = scrpath.replace(":", "_");

		String screenShotPath = System.getProperty("user.dir") + File.separator + srcPath + File.separator
				+ testCaseName + "_" + scrpath + ".png";
		File file = new File(screenShotPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(screencapture, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotPath;
	}

	public static boolean clickElement(WebDriver driver, WebElement we) {
		boolean flag = true;
		try {
			CommonUtils.waitForWebElementtoShowup(driver, we, Setup.MaxWait);
			we.click();
			return flag;
		} catch (Exception e) {
			return false;
		}
	}

	public static Object executeJavascript(WebDriver driver, String script) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		return je.executeScript(script);
	}

	public static Object executeJavascript(WebDriver driver, Object object, String script) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		return je.executeScript(script, object);
	}

	public static void closeOtherPopUps(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();

		// Closing all but the main window
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			if (!winHandle.equals(mainWindow))
				driver.close();
		}

		// Focusing on the main window
		driver.switchTo().window(mainWindow);
	}

	public static void scrollWindow(WebDriver driver, String direction) {
		if (direction.equalsIgnoreCase("Up")) {
			executeJavascript(driver, "scroll(250, 0)");
		} else if (direction.equalsIgnoreCase("Down")) {
			executeJavascript(driver, "scroll(0, 250)");
		}
	}

}
