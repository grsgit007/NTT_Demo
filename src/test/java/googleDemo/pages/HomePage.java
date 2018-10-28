package googleDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import googleDemo.utils.CommonUtils;
import googleDemo.utils.Setup;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "lst-ib")
	@CacheLookup
	public WebElement searchbox;
	
	@FindBy(how = How.ID, using = "hplogo")
	public WebElement googleLogo;
	
	

	public WebElement findBtn(String btnName) {
		return driver.findElement(By.xpath("//input[@value='" + btnName + "']"));
	}

	public WebElement goToPage(String pageName)
	{
		return driver.findElement(By.xpath("//a[text()='"+pageName+"']"));	
	}
	
	
	public void searchKeyword(String keyword) {
		searchbox.sendKeys(keyword);
		googleLogo.click();
//		try {
//		CommonUtils.waitForWebElementtoShowup(driver, findBtn("Google Search"), Setup.MinWait);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		findBtn("Google Search").click();
	}

	public void navigatePage(String string) throws InterruptedException {
		
	CommonUtils.waitForWebElementtoShowup(driver, goToPage("Images"), Setup.MinWait);
	goToPage("Images").click();
	}

}
