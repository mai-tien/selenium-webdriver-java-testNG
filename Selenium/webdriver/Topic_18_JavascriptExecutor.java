package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	//@Test
	public void TC_01_tech_panda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(4);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
		sleepInSecond(4);
	
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(4);
		
		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(4);
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(4);
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(4);
		
		sendkeyToElementByJS("//input[@id='newsletter']","bca" + getRandomNumber() + "@gmail.net");
		
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(4);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(4);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	}
	@Test
	public void TC_02_HTML5_Message() {
		driver.get("https://warranty.rode.com/");
		
		String registerButton ="//button[contains(text(),'Register')]";
		String firstnameTextBox ="//div[contains(text(),'Register')]/following-sibling::div//input[@id='firstname']";
		String surnameTextBox ="//div[contains(text(),'Register')]/following-sibling::div//input[@id='surname']";
		String emailTextBox ="//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']";
		String passwordTextBox ="//div[contains(text(),'Register')]/following-sibling::div//input[@id='password']";
		String confirmPasswordTextBox ="//div[contains(text(),'Register')]/following-sibling::div//input[@id='password-confirm']";

		
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		// vào tab console
		// var element = $x("//input[@id='firstname']")[0];
		//element.validationMessage;
		Assert.assertEquals(getElementValidationMessage(firstnameTextBox),"Please fill out this field.");
		sendkeyToElementByJS(firstnameTextBox,"automation test");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(surnameTextBox),"Please fill out this field.");
		sendkeyToElementByJS(surnameTextBox,"fc");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		
		Assert.assertEquals(getElementValidationMessage(emailTextBox),"Please fill out this field.");
		sendkeyToElementByJS(emailTextBox,"fc@gmail.net");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(passwordTextBox),"Please fill out this field.");
		sendkeyToElementByJS(passwordTextBox,"Password123");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(confirmPasswordTextBox),"Please fill out this field.");
		sendkeyToElementByJS(confirmPasswordTextBox,"Password123");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
	
	}
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain;");
	}
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public int getRandomNumber() {
		return new Random().nextInt(9999);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1000ms = 1s
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}