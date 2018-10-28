package googleDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import googleDemo.utils.CommonUtils;
import googleDemo.utils.Setup;

public class AnotherHomePage {

	WebDriver driver;

	public AnotherHomePage(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement goToPage(String pageName)
	{
		return driver.findElement(By.xpath("//a[text()='"+pageName+"']"));	
	}

	public void navigatePage(String string) throws InterruptedException {
		
	CommonUtils.waitForWebElementtoShowup(driver, goToPage("Images"), Setup.MinWait);
	goToPage("Images").click();
	}

}
